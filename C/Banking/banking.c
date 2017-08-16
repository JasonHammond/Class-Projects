#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<math.h>
#include<stdio.h>

#include <omp.h>
#include "mpi.h"

struct Teller {
    int greetTime;
    int vaultTime;
    int byeTime;
}; //end struct Teller

struct Teller generate_teller();

//----------------------------------------------------------------------------//

struct Teller generate_teller() {
	struct Teller t;
	t.greetTime = 2;
	t.vaultTime = 3;
	t.byeTime = 1;

    return t;
} //end function generate_teller

void balance_load(int cust_count, int vault_count, int *retpos) {
	int r = cust_count % vault_count;

	if(r > *retpos) *retpos = ceil(cust_count / vault_count) + 1;
	else *retpos = ceil(cust_count / vault_count);
}

//----------------------------------------------------------------------------//

int main(int argc, char *argv[]) {

	if(argc != 3) {
		printf("usage: %s (# customers) (# tellers)\n", argv[0]);
	}
	
	else {
	   	int start_time, end_time;
    	int pid, tid;
	    
   		int numCustomers = atoi(argv[1]); //Length of customer line
   		int numTellers = atoi(argv[2]); 	   //Number of tellers (threads)
   		int numVaults; 						//Number of vaults (processes)

    	int required = MPI_THREAD_SERIALIZED;
    	int provided;

		MPI_Init_thread(&argc, &argv, required, &provided);
		MPI_Comm_size(MPI_COMM_WORLD, &numVaults);
		MPI_Comm_rank(MPI_COMM_WORLD, &pid);

    	int work[numVaults];

		if(pid == 0) printf("Setting up, hiring Tellers, installing Vaults...\n");

		omp_set_num_threads(numTellers);
		omp_lock_t Lock;
		omp_init_lock(&Lock);		 

   		struct Teller tellers[numTellers]; //Generate tellers

	   	for(int i=0; i<numTellers; i++) {
		    tellers[i] = generate_teller();
		} //end tellers[] population

		if(pid == 0) printf("Now open for business.\n");

			//sync and start clock
		MPI_Barrier(MPI_COMM_WORLD);
    	start_time = omp_get_wtime();		

		for(int i=0; i<numVaults; i++) {
			balance_load(numCustomers, numVaults, work + i);
		}
		
    	#pragma omp parallel for
		for(int i=0; i < *(work + pid); i++) {
			tid = omp_get_thread_num();
		    sleep(tellers[tid].greetTime);

			omp_set_lock(&Lock);
		    sleep(tellers[tid].vaultTime);
			omp_unset_lock(&Lock);

		    sleep(tellers[tid].byeTime);
			printf("Customer served by Teller p%d-t%d\n", pid, tid);
	//Alternatively (and probably better), use mutex to reduce numCustomers to 0.
	//This would allow for a more robust design for later modifications
	//(individual statistical data for customers, tellers, transaction types...)
    	}
		omp_destroy_lock(&Lock);    	
    
		    //have each teller pull a customer off the line -- teller = thread
		    //only one teller can use a vault at a time -- vault = process
    	MPI_Finalize();

    	end_time = omp_get_wtime();
    	if(pid == 0) {
    		printf("All customers served!\n------------\n");
			printf("Customers: %d \nTellers: %d \nVaults: %d", numCustomers, (numTellers * numVaults), numVaults);
	    	printf("\n\nTotal run time: %d seconds.\n------------\n", (end_time - start_time) );
		}
    } //end else

return 0;
} //end function main

import sys
import random
import string

num_agents = int(sys.argv[1])
num_clients = int(sys.argv[2])
num_relationships = int(sys.argv[3])
num_investees = int(sys.argv[4])
num_transactions = int(sys.argv[5])

aid=[]
cid=[]
iid=[]

outfile = open('testdata.sql', 'w')

outfile.write('delete from agent;\ndelete from client;\ndelete from investee;\ndelete from transaction;\ndelete from advises;\n')



for i in range(0,num_agents):
	aid.append(random.randint(100000,999999))
	p = random.randint(5,20)
	q = random.randint(5,20)
	alast_name = ''.join(random.choice(string.ascii_lowercase) for _ in range(0,p))
	afirst_name = ''.join(random.choice(string.ascii_lowercase) for _ in range(0,q))
	salary = random.randint(30000,250000)

	s = 'insert into agent values (\'' + str(aid[i]) + '\', \'' + alast_name + '\', \'' + afirst_name + '\', \'' + str(salary) + '\');\n'
	outfile.write(s)

for i in range(0,num_clients):
	cid.append(random.randint(100000,999999))
	p = random.randint(5,20)
	q = random.randint(5,20)
	clast_name = ''.join(random.choice(string.ascii_lowercase) for _ in range(0,p))
	cfirst_name = ''.join(random.choice(string.ascii_lowercase) for _ in range(0,q))
	tot_balance = random.randint(0,1000000)
	tot_gain = random.randint(-500000,500000)

	s = 'insert into client values (\'' + str(cid[i]) + '\', \'' + clast_name + '\', \'' + cfirst_name + '\', \'' + str(tot_balance) + '\', \'' + str(tot_gain) + '\');\n'
	outfile.write(s)

for i in range(0,num_relationships):
	aaid = random.choice(aid)
	acid = random.choice(cid)

	s = 'insert into advises values (\'' + str(aaid) + '\', \'' + str(acid) + '\');\n'
	outfile.write(s)

for i in range(0,num_investees):
	iid.append(random.randint(100000,999999))
	p = random.randint(10,20)
	company_name = ''.join(random.choice(string.ascii_uppercase) for _ in range(0,p))
	tot_shares = random.uniform(0.000,999999999999.999)
	share_value = random.uniform(0.01, 999.99)

	s = 'insert into investee values (\'' + str(iid[i]) + '\', \'' + company_name + '\', \'' + str(tot_shares) + '\', \'' + str(share_value) + '\');\n'
	outfile.write(s)

for i in range(0,num_transactions):
	tid = random.randint(100000,999999)
	taid = random.choice(aid)
	tcid = random.choice(cid)
	tiid = random.choice(iid)
	num_shares = random.uniform(0.001,9999.999)
	share_price = random.uniform(0.01,999.99)
	fee = (num_shares * share_price) * 0.03
	day = random.randint(1,31)
	month = random.randint(1,12)
	year = random.randint(2000,2016)

	s = 'insert into transaction values (\'' + str(tid) + '\', \'' + str(taid) + '\', \'' + str(tcid) + '\', \'' + str(tiid) + '\', \'' + str(num_shares) + '\', \'' + str(share_price) + '\', \'' + str(fee) + '\', \'' + str(day) + '\', \'' + str(month) + '\', \'' + str(year) + '\');\n'
	outfile.write(s)

outfile.close()

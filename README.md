# TASK1_API
API backend in Spring

The Micro-service has the below endpoints

1.  http://localhost:8082/api/createAccount
Request
   {
    "name":"samwel wafula ",
    "balance":1000.0,
    "idNumber":"12345"
}
Response

{
    "status": "SUCCESS",
    "responseMessage": "successful",
    "responseCode": "000",
    "account": {
        "accountNo": "981854",
        "name": "samwel wafula ",
        "debit": 0.0,
        "credit": 0.0,
        "balance": 1000.0,
        "idNumber": "12345"
    }
}


The above endpoint creates an acount where it taskes the name, inital balance and idNumber, through the service implementation
it also initializes the sebit and credit values to 0.0 .
The account no is auto-generated by the service and is retuened as an Object account as part on the response message from the endpoint

2.  http://localhost:8082/api/getAccount/12345

Response

{
    "responseCode": 200,
    "responseDesc": "Account found",
    "account": {
        "accountNo": "981854",
        "name": "samwel wafula ",
        "debit": 0.0,
        "credit": 200.0,
        "balance": 1200.0,
        "idNumber": "12345"
    }
}

The above endpoint gets details of the account of a specific account holder. The last parameter passed on the url is the iD Numeber of the acccount holder.
The response body entails details of the account i.e account number, balance of the account, total amount credited to the account, total amount debited from the account

3.  http://localhost:8082/api/transfer

   {
    "sender_account_no":"873126",
    "receiver_account_no":"981854",
    "amount":"100"
}

The above endpoint transfers money from one account to another. The endpoint takes the amount, sender account number, receiver account number. 
The implementation will check if both accounts exists, it will then check if the sender has more money in his account than the amount to be transferred.,
if all is verified, it will execute withdraw() function that debits the amount from the sender account and updates the same to the database, 
it then executes deposit() function that credits the receiver account and updates the same on the database.



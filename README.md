# Coupon System

A REST web service that enables companies to offer coupons and customers to purchase them. Built using Maven and Spring Boot. Utilizes Hibernate to manage database calls.


* [Supported URLs](https://github.com/MirYeh/CouponSystem#supported-urls)
	* [Company URLs](https://github.com/MirYeh/CouponSystem#company-urls)
	* [Customer URLs](https://github.com/MirYeh/CouponSystem#customer-urls)
	* [General URLs](https://github.com/MirYeh/CouponSystem#general-urls)
* [Examples](https://github.com/MirYeh/CouponSystem#examples)
	* [Register Company](https://github.com/MirYeh/CouponSystem#register-company)
	* [Add Coupon](https://github.com/MirYeh/CouponSystem#add-coupon)
	* [Search Coupons](https://github.com/MirYeh/CouponSystem#search-coupons)
	* [Purchase Coupon](https://github.com/MirYeh/CouponSystem#purchase-coupon)


## Supported URLs



### Company URLs
Company URLs start with _/cp_


URL					| HTTP Method 		| Description
--------------------|-------------------|-----------------
/register			| POST				| register a company
/login				| POST				| log in to existing company account
/logout/{accountId}	| GET				| log out of account
/{accountId}		| GET, PUT, DELETE	| get, update and remove account
/coupons			| GET, POST			| get account's coupons and add coupon to account
/coupons/{couponId}	| GET, PUT, DELETE	| get, update and remove specific coupon of account



### Customer URLs
Customer URLs start with _/u_


URL					| HTTP Method 		| Description
--------------------|-------------------|-----------------
/register			| POST				| register a customer
/login				| POST				| log in to existing customer account
/logout/{accountId}	| GET				| log out of account
/{accountId}		| GET, PUT, DELETE	| get, update and remove account
/coupons			| GET, POST			| get purchased coupons and purchase coupon
/coupons/{couponId}	| GET				| get purchased coupon



### General URLs


URL								| HTTP Method| Description
--------------------------------|------------|-----------------
/coupons						| GET		 | get active coupons (accepts min, max and type parameters)
/coupons/{couponId}				| GET		 | get specific coupon
/companies						| GET		 | get all companies
/companies/{companyId}			| GET		 | get a specific company
/companies/{companyId}/coupons	| GET		 | get all coupons of a specific company



## Examples



### Register Company

Send POST request to _/cp/register_ with body:
```
{
    "username": "GoodSales",
    "password": "8Hds9am33l",
    "email": "support@goodsales.com"
}
```
Expected response:
status: 201 Created
Location: _/cp/{id}_



### Add Coupon

Send POST request to _/cp/coupons_ with body:
```
{
  "title": "Some title",
  "startDate": "2018-01-01",
  "endDate": "2019-01-01",
  "amount": 200,
  "type": "Electronics",
  "price": 50,
  "message": "Some message"
}
```
Expected response:
status: 201 Created
Location: _/coupon/{id}_


### Search Coupons

Send GET request to _/coupons?min=5&max=1000&type=electronics_

Expected response:
```
[
    {
        "id": 1,
        "owner": {
            "id": 1,
            "username": "GoodSales",
            "password": "8Hds9am33l",
            "email": "support@goodsales.com",
            "joined": "2018-01-01"
        },
        "title": "Some title",
        "startDate": "2018-01-01",
        "endDate": "2019-01-01",
        "amount": 200,
        "type": {
            "id": 1,
            "name": "Electronics"
        },
        "price": 50,
        "isActive": true,
        "message": "Some message",
    }
]
```


### Purchase Coupon

Send POST  request to _/u/coupons_ with body:
```
{
	"coupon": {
		"id": 1
	},
	"amount": "3"
}
```
Expected response:
Status: 200 OK
Location: /coupon/{id}




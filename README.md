# fyle-bank-app

A Java app, which is deployed to Heroku. It is build on spring-boot and postGres.

## Running Locally

### Prerequisites

1. Make sure Java and Maven installed.
2. Install the Heroku cli (https://cli.heroku.com/).

### Running

1. Please clone the repository from the above given URL.
2. Move to the cloned directory
3. Run mvn install to install all the required dependencies
4. Now move to next step to deploy it on heroku.


## Deploying to Heroku

1. To create a heroku app run command "Heroku create" which will create a new app on a particular url.
2. To push the application code to deployed app run "git push heroku master"
3. After successful push you can go and check for the app.



## Running the Demo APIs

When you will move to the XYZ.herokuapp.com you will be promted as access denied.

Now First of all user will have to generate a authentication token and further it will be authenticated to use the app using that token

 1. The authentication or SignIn API (GET API)
   By providing valid Username and Password in this API you will be retured with a particular token which would be valid for 5 days.It first validates the input from the database and if there is a correponding record in the DB it returna an JWT key else it reurn error.
      
    curl 'https://damp-island-53265.herokuapp.com/auth/signin?username=shrikant&password=shrikant'
   
 2. Getting the Branch Details of a single branch by its IFSC code
    
    I have created restful service using graphql so endpint is same for other apis too. For example you can use the api by using the curl command.
    
        curl 'https://damp-island-53265.herokuapp.com/getBranch?Ifsc=ASBL0000047' -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHJpa2FudCIsImlhdCI6MTU2NjczNjg0MCwiZXhwIjoxNTY3MTY4ODQwfQ.AOzArE5uD-D4mku-VWWaHKR1latd-4ts4U3gkY1CU_c'
    
 3. Getting the  Details of a branches of a particular bank in particular city
  
        curl 'https://damp-island-53265.herokuapp.com/getBranches?city=MUMBAI&bankName=ABHYUDAYA%20COOPERATIVE%20BANK%20LIMITED&limit=4&offSet=1' -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHJpa2FudCIsImlhdCI6MTU2NjczNjg0MCwiZXhwIjoxNTY3MTY4ODQwfQ.AOzArE5uD-D4mku-VWWaHKR1latd-4ts4U3gkY1CU_c'
          

## following users can be used to generate token.

1.UserName : shrikant, Password : shrikant    
2.Username : bhawesh, Password : bhawesh
 
 
    
  
 
   
   
  
    

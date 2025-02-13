# Uni version of the Mobile.bg

**Used design patterns in the project:**
- Memento
- Observer
- Repository

## How does the app starts:

- Login
- Register
- Have a look as guest

#### Login:
1. Enter username
2. Enter password

#### Register:
1. String name;
2. String password;
3. String email;
4. UUID id; **this is generated:**
5. String phoneNumber;
6. String address;

### Options for logged user
1. create a listing
2. delete a listing
3. show me my listings
4. show all listings
5. search a listing,
   - you have filters: range filter, case insensitive, exact value, field extractor
6. history of searching

### Options for guest
1. show all listings
2. search a listing,
   - you have filters: range filter, case insensitive, exact value, field extractor
3. history of searching


## Notes to myself for implementation

### How to connect them
1. Services(things which has operations: add, delete and so on) - inject in repositories
2. Repositories has the data from the "database". In my case: hard coded data
3. Add "Bigger" services which are using the smaller ones
   - example: Listing service, combining User service, Product service
4. Using the "Bigger" services - apply the searching and filters.

service:
link user - listing - product

service 
link listing - product
addListing()



user - guestService, LoggedUserService ?
addUser()
DeleteUser(us, pw)
FindUser(username,password)

mixedService should be able to work with these

changePassword(newPwd)
changeName(username,password, newName)
changeEmail(newEmail)
getEmail()
changePhoneNumber(numbr)
getPhoneNumber(numbr)
getAddress()
changeAddress(address)

add listing
show all listings
show my listings
search product == open listing






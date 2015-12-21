# Complex SQLite Structure Problem
This repository was created to provide a working sample for an issue I'm having
with SQLite and to try and get a better understanding of how to solve this.

I have some prior experience with SQLite, but that was mostly storing simple
objects without many dependencies. The current data I'm working with is a bit
more complex. The JSON shown below shows the structure of the data as returned
by an API.

```json
{
  "group": {
    "id": 138,
    "name": "Group 1",
    "balance": -5487.99,
    "users": [
      {
        "id": 78,
        "name": "Alice",
        "balance": -1488.46
      },
      {
        "id": 105,
        "name": "Bob",
        "balance": 6976.45
      }
    ],
    "bills": [
      {
        "id": 342,
        "title": "Bill 2",
        "amount": 400,
        "users": [
          {
            "id": 78,
            "name": "Alice",
            "paid": 400
          },
          {
            "id": 105,
            "name": "Bob",
            "owes": 133.33
          }
        ],
        "createdBy": 78,
        "notes": [
          {
            "id": 258,
            "message": "Note 1",
            "user": {
              "id": 78,
              "name": "Alice"
            }
          }
        ]
      }
    ]
  }
}
```
Which can be represented as a simple POJO in Java. The code belows shows the
group class:
```java
public class Group {
    public int id;
    public String name;
    public double balance;
    public List<User> users;
    public List<Bill> bills;
}
```
Based on this structure I've made the following structure for the database:

![Database structure](https://raw.githubusercontent.com/fdorssers/complex-sqlite-structure/master/images/dbstructure.png)

At the moment I can pass a simple class representing this structure to a
function which stores it in the database in the right way. However now I'd also
like to create a function which I can call to get the same object back.
This is where the primary issue is. I can't really figure out if there's an
efficient way using SQLite to do this. Obviously I could just blindly go
through each different table, collect the values one by one and put them
together, but that doesn't seem right.

So my question is: **How can I efficiently get all the data back from SQLite in
the same structure as I began with?**

The current structure is somewhat loosely based on the sample provided by
[SQLBrite](https://github.com/square/sqlbrite), initially it looked a lot more
like that sample, but I toned it down so I could first get the basics down.

All the storing and reading happens in `MainActivity.java`. The simple Java
objects representing the data are stored in the `model` package. The `db`
package contains a Dagger 2 module used to initialize the `SQLiteOpenHelper`
(initially this also contained initializations for SQLBrite), and the `table`
packages contains Java classes representing each table that's in the database.

The current code in the application is in no way perfect. There are still
several issues that I have to fix:
- The database is always recreated when pressing the first button to prevent
problems
- Storing the information is quite verbose
- Doesn't take uniqueness into consideration, adding the same data without
destroying the database would throw an exception

And probably a few more that I haven't noticed yet.

Until how I've actually been using [Realm](https://github.com/realm/realm-java),
which works great, and I can always go back to using that as a NoSQL
alternative. However I'd like to get a better understanding of SQLite too. I'm
also open to using an SQLite library to make life easier, for example
[DBFlow](https://github.com/Raizlabs/DBFlow),
[Cupboard](https://bitbucket.org/littlerobots/cupboard),
[StorIO](https://github.com/pushtorefresh/storio), or just a wrapper for SQLite
like [SQLBrite](https://github.com/square/sqlbrite).

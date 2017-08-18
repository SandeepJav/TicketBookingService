## Ticket Booking Service
Ticket Booking Service applicatoin enables to you find and resever seats in a venue.

## Assumptions
Venue is of 10 rows and 10 columns consisting of 100 seats.

Once seat is being hold by any customer it will be released and made available after every 5 seconds.

## Design
Seat.java model object  
SeatHold.java model object
TicketService.java service interface
TicketServiceImpl.java Service Implementation Class
TicketServiceImplTest.java Test Class for Service Implementation Class
NoAvaialbleSeatsException.java User Defined Exception Class
SeatHoldExpiredException.java User Defined Exception Class

### Prerequisites

[Git] 
[JAVA]
Be sure that your `JAVA_HOME` environment variable points to the `jdk1.8.0` folder
extracted from the JDK download.

[Maven]

[Eclipse] IDE is optional

### Check out sources
In your root directory plesae run below command

$>git clone https://github.com/SandeepJav/ticketBookingService-.git

navidate to the project folder ticketservice

## Building from Source
### Compile and test; build all jars, distribution zips, and docs

Run below command to clean project target folders

$> mvn clean

Run below command to compile the code

$> mvn compile

Run below command to run all unit test cases

$>mvn test

## Importing source code into Eclipse

Generate general eclipse configuration files by running below maven command
$>mvn eclipse:eclipse

In eclipse, go to File-->Import-->Existing Projects in Workspace-> select root directory

## Contributing
[Pull requests] are welcome; 

## Staying in Touch
Email me [sanj.python@gmail.com]

## License


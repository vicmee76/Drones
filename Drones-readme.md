## Java Drones Project.
_By :  Victor Daberechi Nwogu_

_LinkedIn : [linkedin.com/in/victor-nwogu-682b98110](https://www.linkedin.com/in/victor-nwogu-682b98110)_

_Email : vicmee76@gmail.com, vicmee76@hotmail.com_

_GitHub : https://github.com/vicmee76_

_Mobile : +2349053631139, +2347012651218_

---

## Introduction
This project is a Java base REST API application that's demonstrates different implementation of the task given for a drone new technology. Below are different implementations that has been done on the project:

- Validate and register a drone.
- Validate and register a medication.
- Validate and load a drone with different medication items.
- Get all medications for a given drone by passing the drone ID.
- Get all available drones for loading.
- Check the battery level for a given drone.
- Prevent a drone from been in loading state if the battery level is less than 25%
- Prevent a drone from being loaded if the sum of all medications weight is greater and the weight of the drone.

---

## Clone Repo
Kindly clone the repo with the given link : [https://oauth:glpat-2MiojGF-G2Aq_-3U26Zs@gitlab.com/musala-coding-tasks-solutions/victor-daberechi-nwogu.git](https://oauth:glpat-2MiojGF-G2Aq_-3U26Zs@gitlab.com/musala-coding-tasks-solutions/victor-daberechi-nwogu.git)

You can chose to delete the 'target' folder and build the application to re-download its dependencies.

---

## Database Config.
This application makes use of a local DB (H2 Database) which can be accessed using these details 
- Url : http://localhost:8080/h2-console
- Database Source : jdbc:h2:file:./data/demo
- Username : sa
- Password : password

---

## Endpoints
**_base url : http://localhost:8080/_**

- ## **Register a Drone**
  ### _POST Method_
  This endpoint basically validate model inputs before creating a drone.

  > Note : Checks done on this service includes : check for already exiting drone using its serial number.
    
  Endpoint url : _http://localhost:8080/api/v1/drone/register_

  ### *Body Params*
  Parse body object in JSON format.
  ```sh
  {
    "serialNumber": "DRN-123",
    "model": "Cruiserweight",
    "weight": 500,
    "battery": 50,
    "state": "IDLE"
  }
  ```
  ---
  ### *Validation error results*
  This error result can be **any** of the following with status code **400**
  ```sh
  {
    "Errors":  
    [
        "Drone battery level should not be 1 or lesser",
        "Drone state should not be null",
        "Drone serial number should not be null",
        "Drone model should not be null",
        "Drone weight should not be null",
        "Drone weight should not be more than 500kg",
        "Drone battery level should not be more than 100%"
    ]
  }
  ```
  ---
  ### *Failure error response*
  Error response with a **01** response code and a message attached to it.
  ```sh
  {
    "responseCode":  "01",
    "responseMsg":  "Serial Number already exits",
    "drone":  null,
    "medications":  null
  }
  ```
  ---
  ### *Success response*
  Displays the saved drone model with a **00** responseCode and a success response message.
  ```sh   
  {
    "responseCode":  "00",
    "responseMsg":  "Success",
    "drone":  {
        "id":  14,
        "serialNumber": "DRN-123",
        "model": "Cruiserweight",
        "weight": 500,
        "battery": 50,
        "state": "IDLE"
        },
    "medications":  null
  }
  ```
  &nbsp;

- ## **Create Medication Item**
  ### _POST Method_
  This endpoint validate medication items before creating the item.

  > Note : Implementation checks done on this endpoint includes :
  1 . Check if a medication already exits using medication code.

  _Endpoint url : http://localhost:8080/api/v1/medication/register_

  ### *Body Params*
  Parse body object in JSON format.
  ```sh
  {
      "name"  :  "ACss123",
      "weight":  53,
      "code"  :  "12345R11T",
      "image" :  "https://image.com"
  }
  ```
  ---
  ### *Validation error results*
  This error result can be **any** of the following with status code **400**
  ```sh
  {
      "Errors":  
      [
          "Medication code should not be null",
          "Medication name should not be null",
          "Medication weight should not be null",
          "Medication image url should not be null",
          "Allowed only letters, numbers, ‘-‘, ‘_’",
          "Allowed only upper case letters, underscore and numbers",
          "Medication weight should not be more than 500kg"
      ]
  }
  ```
  ---
  ### *Failure error response*
  Error response with a **01** response code and a message attached to it.
  ```sh
  {
      "responseCode": "01",
      "responseMsg": "Medication code already exits",
      "drone":  null,
      "medications": null
  }
  ```
  ---
  ### *Success response*
  Displays the saved medication model with a **00** responseCode and a success response message.
  ```sh   
  {
      "responseCode":  "00",
      "responseMsg":  "Success",
      "drone":  null,
      "medications":  [
          {
              "id": 7,
              "name": "ACss123",
              "weight": 53,
              "code": "12345R11T",
              "image": "https://image.com"
          }
      ]
  }
  ```
&nbsp;

- ## Load Medication
  ### _POST Method_
  This endpoint is used to load medication items into a given drone by passing the IDs of the medications, and a single drone ID.

  > Note : checks done here are,
  > - Check if an empty object was passed.
  > - Check if a given drone exits.
  > - Check if drone is in idle or loading state before it can be used .
  > - Check drone battery life is greater than 25% before it can be used.
  > - Check if each medication exits before it can be transferred into the drone.
  > - Check if the sum of medication items weight  is lesser than the weight of the drone before the drone can be used.

  _Endpoint url : http://localhost:8080/api/v1/dispatcher/load-with-medication_

  ### *Body Params*
  Parse body object in JSON format.

  ```sh
  {
      "droneId":  2,
      "medicationId":  [ 2, 4]
  }
  ```
  ---
  ### *Failure error response*
  Error response with a **01** response code and a message attached to it.
  - _Drone not in idle or loading state_
  ```sh
  {
      "responseCode": "01",
      "responseMsg": "Drone needs to be idle or in loading state before medications can be loaded into it.",
      "drone": null,
      "medications": null
  }
  ```
  - _Drone not found or empty_

  ```sh
  {
      "responseCode": "01",
      "responseMsg": "Drone cannot be empty",
      "drone":  null,
      "medications":  null
  }
  ```
  - _Medication list empty or not found_
  ```sh
  {
      "responseCode":  "01",
      "responseMsg":  "Medication list cannot be empty",
      "drone":  {
          "id":  2,
          "serialNumber":  "DRONE123",
          "model":  "Cruiserweight",
          "weight":  500.00,
          "battery":  90,
          "state":  "IDLE"
      },
      "medications":  null
  }
  ```
  - _Drone battery less than 25%_
  ```sh
  {
      "responseCode": "01",
      "responseMsg": "Drone batter is less than 25%, Please select another drone with higher battery power.",
      "drone": null,
      "medications": null
  }
  ```
  - _Drone weight check with all medication weight_
  ```sh
  {
      "responseCode":  "01",
      "responseMsg":  "The weight of the drone is less that the total weight of medications, Please use a drone with higher weight.",
      "drone":  {
          "id":  1,
          "serialNumber":  "DRONE123",
          "model":  "Lightweight",
          "weight":  250.00,
          "battery":  100,
          "state":  "LOADING"
      },
      "medications":  [
          {
              "id":  2,
              "name":  "AB",
              "weight":  290.00,
              "code":  "1234567890",
              "image":  "https://image.com"
          },
      ]
  }
  ```
  ---
  ### *Success response*
  Displays the loaded medication items and the drone  model with a **00** responseCode and a success response message.
  ```sh   
  {
      "responseCode":  "00",
      "responseMsg":  "Success",
      "drone":  {
          "id":  2,
          "serialNumber":  "DRONE123",
          "model":  "Cruiserweight",
          "weight":  500.00,
          "battery":  90,
          "state":  "LOADED"
      },
      "medications":  
      [
          {
              "id":  2,
              "name":  "AB",
              "weight":  70.00,
              "code":  "1234567890",
              "image":  "https://image.com"
          },
          {
              "id":  4,
              "name":  "AC",
              "weight":  70.00,
              "code":  "1234567890AS",
              "image":  "https://image.com"
          }
      ]
  }
  ```
&nbsp;

- ## Get Drone Medications
  ### _GET Method_
  This method basically get all medications for a given drone by passing a single drone ID.

  _Endpoint url : http://localhost:8080/api/v1/medication/medications-for-drone/{id}_

  	 {id} : Number 

  ### *Failure response*
  - _No drone found_
  ```sh
  { 
      "responseCode":  "01",
      "responseMsg":  "No drone was found for this ID",
      "drone":  null,
      "medications":  null
  }
  ```
  - _No medication found for the given drone._
  ```sh
  {
      "responseCode":  "01",
      "responseMsg":  "No medication was found for this drone",
      "drone":  null,
      "medications":  null
  }
  ```
  ---
  ### *Success response*
  Displays the loaded medication items and the drone model for a given drone ID, with a **00** responseCode and a success response message.
  ```sh
  {
      "responseCode":  "00",
      "responseMsg":  "Success",
      "drone":  {
          "id":  2,
          "serialNumber":  "DRONE123",
          "model":  "Cruiserweight",
          "weight":  500.00,
          "battery":  90,
          "state":  "LOADED"
      },
      "medications": 
       [
          {
              "id":  2,
              "name":  "AB",
              "weight":  70.00,
              "code":  "1234567890",
              "image":  "https://image.com"
          },
          {
              "id":  4,
              "name":  "AC",
              "weight":  70.00,
              "code":  "1234567890AS",
              "image":  "https://image.com"
          }
      ]
  }
  ```

&nbsp;

- ## Get Available Drones
  ### _GET Method_
  This method get all drones in idle or loading state.

  > Note : Drones in loading state are still available since the loading process has not been closed.
  Also, An empty list is returned if no available drone is found.

  _Endpoint url : http://localhost:8080/api/v1/drone/drones-for-loading_

  ### *Body response*
  ```sh
  [
      {
          "id":  1,
          "serialNumber":  "DRONE123",
          "model":  "Lightweight",
          "weight":  250.00,
          "battery":  100,
          "state":  "LOADING"
      },
      {
          "id":  14,
          "serialNumber":  "DRON-123",
          "model":  "Cruiserweight",
          "weight":  500.00,
          "battery":  13,
          "state":  "IDLE"
      }
   ]
  ```
  ---

  &nbsp;

- ## Get Drone Battery
  ### _GET Method_
  This method gets a drone object for a given drone which includes its battery life.

  _Endpoint url : http://localhost:8080/api/v1/drone/drone-battery-level/{id}_

  	 {id} : Number 

  ### *Failure response*
  - _No drone found_
  ```sh
  { 
      "responseCode":  "01",
      "responseMsg":  "No drone found with this id",
      "drone":  null,
      "medications":  null
  }
  ```
  ---
  ### *Success response*
  Displays a drone object which includes battery life.
  ```sh
  {
      "responseCode":  "00",
      "responseMsg":  "Success with drone batter",
      "drone":  {
          "id":  3,
          "serialNumber":  "DRONE123",
          "model":  "Cruiserweight",
          "weight":  500.00,
          "battery":  90,
          "state":  "LOADED"
      },
      "medications":  null
  }
  ```
  ---

### END

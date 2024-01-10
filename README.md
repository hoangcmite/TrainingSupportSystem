# S-Courses (Training Support Systems)
# About : 
   ### * Description : 
   S-Courses (Training Support System) is a software project built to help institutions, students, students and teachers manage their teaching and learning. For each different user object, there will be different functions and activities. All users have their accounts secured with User Authentication. A common feature for all users is to be able to login, register, view personal information, change password and retrieve password. S-Course will help users have a good experience, quick access, diverse features to help users monitor, manage and edit quickly. This system support for the below types of user
   - Guest: unregistered users
   - Trainee: register and/or be sent to participate in classes, courses, and learn lessons in the system
   - Trainer: manage students, plan class learning content, evaluate students learning process
   - Supporter: support teachers in implementing classes (class configuration, class schedule, class list), perform work with classes of subjects as assigned by manager (configured by class), support processing and replying to contacts from the web (Web contacts)
   - Manager: subject leader, in charge of the assigned subjects: subject configuration, class management
   - Admin: full authority, acts as the system administrator
   ### * Role/Main Tasks : Member/Backend( Must complete all requirements assigned ( backend and fontend of requirement )  )
   ### * Languages programing, libraries and frameworks : 
  - Java, JavaScript, Html, Css, Boostrap, Jquery, SweetAlert
  ### * Link Video Description :  
# My features are assigned: 
## 1. Guest (Login)
#Requirnment: 
- This is a screen which allows the user to enter email & password to login on this page or directly use google account to login, there are also options for users to register new information or reset the password for the case he or she forgets it

Login
![1.1  Login](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1a.PNG)

Login Google
![1.2  Login Google](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1b.PNG)

## 2. Admin
#### 2.1 Screen Content Management for Role Admin
#Requirnment: 
- When the user login successfully with role Admin and if selects the dashboard item, the screen only displays the content that the Admin has permission to access and manage.

Content Management
![2.1  Content Management](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1c.PNG)

#### 2.2 Screen Setting List
#Requirnment: 
- List out the current Settings information. From this page, the user can link to the Setting Details page for adding new or updating a specific Setting.
- Filters: Type, Status
- Search: by Title or Value
- Columns: Id, Type, Title, Value, Display Order, Status
- Row Actions: View/Edit, Activate/Deactivate, Sort
- Status: Active, Inactive

Setting List
![2.2.1  Setting List](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1c1.PNG)

Status
![2.2.2  Status](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1c2.PNG)

#### 2.3 Screen Setting Details
#Requirnment: 
- Allow the user to input the Setting information for adding or updating
- Fields: Type, Title, Value, Display Order, Status, Description  
- Add a new setting for input conditions and required data

Setting Update
![2.3.1  Setting Update](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1c4.PNG)

Setting Add
![2.3.2  Setting Add](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1c3.PNG)

## 3. Manager
#### 3.1 Screen Content Management for Role Manager
#Requirnment: 
- When the user login successfully with role Manager and if selects the dashboard item, the screen only displays the content that the Manager has permission to access and manage.

Content Management
![3.1  Content Management](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1d.PNG)

#### 3.2 Screen Class Setting List
#Requirnment:
- List out the Settings for a specific active class in the system. 
- The Supporter can view the list of Settings for the classes that he/she are assigned (read-only)
- From this page, the user can link to the Class Setting Details page for adding new or updating a specific Setting.

Class Setting List
![3.2.1  Class Setting List](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1d1.PNG)

Status
![3.2.2  Status](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1d2.PNG)

#### 3.3 Screen Class Setting Details
#Requirnment:
- Allow the user to input Class Setting information for adding or updating
- The user can not change the Class and setting type when updating the Setting
- The Supporter can view the Class Setting information only

Class Setting Details
![3.3  Class Setting Details](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1d3.PNG)

#### 3.4 Screen Assignment List
#Requirnment: 
- List out the Assigments for a specific active subject in the system. From this page, the user can link to the Assignment Details page for adding new or updating a specific Assignment.

Assignment List
![3.4 Assignment List](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1d4.PNG)

## 4. Supporter
#### 4.1 Screen Content Management for Role Supporter
#Requirnment: 
- When the user login successfully with role Supporter and if selects the dashboard item, the screen only displays the content that the Supporter has permission to access and manage.

Content Management
![4.1  Content Management](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1e.PNG)

#### 4.2 Screen Trainee List
#Requirnment:
- List out the Trainees for a specific class in the system
- The list also include the evaluated grades of the trainees (calculated automatically)
- When changing trainee status to Dropped Out, the user need to input the dropout date
- The Trainer also have the same access rights as the Supporter to the trainees list of the classes that he/she are assigned
- From this page, the user can link to the Trainee Import & Trainee Details page s

Trainee List
![4.2.1  Trainee List](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1e1.PNG)

Status
![4.2.2  Status](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1e2.PNG)

#### 4.3 Screen Trainee Details
#Requirnment:
- Allow the user to input class trainee information for updating
- The Trainer can also update class trainee information
- Trainee basic information are read-only: full name, user name, email
- Class trainee evaluation are read-only: on-going evaluation, final evaluation, topic evaluation
- Class trainee information are editable: status, drop-out date, note

Trainee Details
![4.3  Trainee Details](https://gitlab.com/hoangcmhe153602/s-course/-/raw/main/ScreenLayout/1e3.PNG)


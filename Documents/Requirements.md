# Requirements

## index
- [Requirements](#requirements)
  - [index](#index)
  - [Customer Requirements](#customer-requirements)
  - [List of available SDKs](#list-of-available-sdks)
    - [Web Development:](#web-development)
    - [Mobile Development:](#mobile-development)
    - [Machine Learning and Data Science:](#machine-learning-and-data-science)
    - [Desktop Development:](#desktop-development)
    - [Game Development:](#game-development)
    - [Cloud Computing and Infrastructure:](#cloud-computing-and-infrastructure)
  - [System Reqierments](#system-reqierments)
    - [1.  User Shall be able to log in and out from the SDK Manager](#1--user-shall-be-able-to-log-in-and-out-from-the-sdk-manager)
    - [2. The SDK shall list all the installable SDKs.](#2-the-sdk-shall-list-all-the-installable-sdks)
  - [System Tasks](#system-tasks)


## Customer Requirements 
* SDK Manager shall download a SDK from a list of SDKs
* SDK Manager shall update already isntalled SDKs on your system
* SDK Manager shall remove SDK from your system if wanted
* The SDK shall list all the installable SDKs.
* User Shall be able to log in and out from the SDK Manager
* Admin shall be able to edit the List of SDKs


## List of available SDKs
### Web Development:
1. **React**: A JavaScript library for building user interfaces.
2. **Vue.js**: A progressive JavaScript framework for building interactive web interfaces.
3. **Angular**: A platform and framework for building single-page client applications using HTML and TypeScript.
4. **Express.js**: A minimalist web application framework for Node.js.
5. **Django**: A high-level Python web framework that encourages rapid development and clean, pragmatic design.

### Mobile Development:
1. **Flutter**: Google's UI toolkit for building natively compiled applications for mobile, web, and desktop from a single codebase.
2. **React Native**: A framework for building native applications using React.
3. **SwiftUI**: A framework to build user interfaces across all Apple platforms using Swift language.
4. **Android SDK**: The official development kit for Android app development.
5. **Ionic**: A complete open-source SDK for hybrid mobile app development.

### Machine Learning and Data Science:
1. **TensorFlow**: An open-source machine learning framework developed by Google.
2. **PyTorch**: An open-source machine learning library developed by Facebook.
3. **scikit-learn**: A machine learning library for Python built on top of NumPy, SciPy, and Matplotlib.
4. **Keras**: A high-level neural networks API, written in Python and capable of running on top of TensorFlow, CNTK, or Theano.
5. **Pandas**: A fast, powerful, flexible, and easy-to-use open-source data analysis and manipulation library for Python.

### Desktop Development:
1. **Electron**: A framework for building cross-platform desktop applications using web technologies.
2. **Qt**: A cross-platform application framework for developing applications that can run on various software and hardware platforms.
3. **GTK**: A multi-platform toolkit for creating graphical user interfaces.
4. **JavaFX**: A set of graphics and media packages that enable developers to design, create, test, debug, and deploy rich client applications.
5. **Windows SDK**: The set of headers, libraries, and tools needed to develop applications for Windows.

### Game Development:
1. **Unity**: A cross-platform game engine developed by Unity Technologies.
2. **Unreal Engine**: A complete suite of development tools made for anyone working with real-time technology.
3. **Godot Engine**: An open-source game engine with a full feature set for 2D and 3D game development.
4. **Cocos2d-x**: A cross-platform open-source game development framework.
5. **Phaser**: A fast, free, and fun open-source framework for Canvas and WebGL-powered browser games.

### Cloud Computing and Infrastructure:
1. **AWS SDK**: Software development kit for working with Amazon Web Services (AWS).
2. **Azure SDK**: Libraries, tools, and documentation for working with Microsoft Azure.
3. **Google Cloud SDK**: A set of tools to manage resources and applications hosted on Google Cloud.
4. **Terraform**: An open-source infrastructure as code software tool created by HashiCorp.
5. **Kubernetes SDK**: Libraries and tools for interacting with Kubernetes clusters.

## System Reqierments

### 1.  User Shall be able to log in and out from the SDK Manager



- [x] Create a User model
- [x] Create a Login View 
- [x] Create a Login Viewmodel
- [x] Make password like a dot
- [x] Login should be able with username
- [x] Create a connection to MongoDB
- [x] Create a User DAO 
- [x] Create a Register View
- [x] Create a Register Viewmodel
- [x] Create a User Session 
- [x] Create DI functionalty for the UserSession
- [ ] Auth password on login
- [ ] Update System Design (UML) (No Prio)
- [ ] Login functionality is Done

### 2. The SDK shall list all the installable SDKs.

- [x] Create a MainMenu View
- [x] Create a logout functionality on MainMenu
- [ ] Create a ShowSdk View
- [ ] Create a UserSdk Model
- [ ] Create a new DAO for SDK
- [ ] Create a MainMenu ViewModel
- [ ] Create a ShowSdk ViewModel
- [ ] TBD

## System Tasks 


| Task | Status | Note| Test| Improvements|
|------|-----------|-----------|------| ----- |
|Create a connection to MongoDB| :ballot_box_with_check:| - | :ballot_box_with_check:| None|
|Create a User DAO | :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Create a User model| :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Create a Login View | :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Create a Login Viewmodel| :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Create a Register View| :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Create a Register Viewmodel| :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Make password like a dot| :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Create a User Session | :ballot_box_with_check: | - | :ballot_box_with_check: | None|
|Create DI functionalty for the UserSession| :ballot_box_with_check: | - | :ballot_box_with_check: | None|



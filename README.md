# 💼 Job Recruitment System

A **Java-based console application** that connects job seekers and recruiters, with intelligent resume ranking using **TF-IDF algorithm**.

---

## 📌 Abstract

The Job Portal System is a console-based Java application designed to bridge the gap between job seekers and recruiters. Recruiters can post job vacancies with required skills, and job seekers can register, upload resumes, and apply for jobs.

The key highlight of this project is the implementation of the **TF-IDF (Term Frequency–Inverse Document Frequency)** algorithm for resume ranking. It automatically ranks candidates based on how well their resumes match job requirements, helping recruiters efficiently identify the most suitable candidates.

The system follows **Object-Oriented Programming (OOP)** principles with modular design, encapsulation, and role-based access.

---

## 🚀 Introduction

### 🔹 Project Overview

This system automates the recruitment process by providing a platform for:

* Job posting
* Application submission
* Intelligent candidate ranking

It solves the problem of manually screening multiple resumes.

---

## 🎯 Objectives

* Build a user-friendly console application
* Implement secure login and registration
* Enable job posting with required skills
* Allow resume creation and updates
* Implement TF-IDF based ranking
* Provide role-based access (Recruiter / Job Seeker)

---

## ✨ Features

### 👨‍💻 Job Seeker

* Register and login
* Create and update resume
* View all jobs
* Search jobs by keyword
* Apply for jobs

### 👨‍💼 Recruiter

* Post new jobs
* View job listings
* View applicants
* Rank applicants using TF-IDF

---

## ⚙️ System Requirements

### 💻 Hardware

* Processor: Intel Core i3 or higher
* RAM: 4 GB minimum
* Storage: 100 MB

### 🖥️ Software

* OS: Windows / Linux / MacOS
* JDK: Version 8+
* IDE: Eclipse / IntelliJ / NetBeans

---

## 🏗️ System Architecture

The system is based on modular OOP design with key classes:

* `User` → Stores user details and resume
* `Job` → Stores job details and required skills
* `Application` → Links users to jobs and stores TF-IDF score
* `JobSystem` → Core logic and processing
* `Main` → Entry point

---


## 🧠 Implementation Details

### 📊 Data Structures Used

| Data Structure           | Purpose              |
| ------------------------ | -------------------- |
| ArrayList<User>          | Store users          |
| ArrayList<Job>           | Store jobs           |
| ArrayList<Application>   | Store applications   |
| HashMap<Integer, Double> | Store ranking scores |
| List<String>             | Store resumes        |
| Arrays.asList()          | Convert skills       |

---

### 🔧 Key Methods

#### 👤 User Management

* `register()` → Create account
* `login()` → Authenticate user

#### 💼 Job Management

* `postJob()` → Add job
* `viewJobs()` → Show jobs
* `searchJob()` → Search jobs

#### 📄 Applications

* `applyJob()` → Apply for job
* `viewApplicants()` → View candidates

---

## 🧪 Testing & Results

### ✅ Test Cases

| Test Case    | Input          | Expected Output | Result |
| ------------ | -------------- | --------------- | ------ |
| Registration | Valid details  | Success         | ✔ Pass |
| Login        | Wrong password | Error           | ✔ Pass |
| Post Job     | Valid data     | Job created     | ✔ Pass |
| Apply Job    | Job ID         | Success         | ✔ Pass |
| Ranking      | Multiple users | Sorted list     | ✔ Pass |

---

### 📊 TF-IDF Results

| Applicant | Score  | Rank |
| --------- | ------ | ---- |
| Alice     | 0.2345 | 1    |
| Bob       | 0.0891 | 2    |
| Charlie   | 0.0000 | 3    |

---

### ⚡ Performance

* Ranking time: < 1 second (100 users)
* Efficient memory usage
* Scalable design

---

## 🏁 Conclusion

This project successfully implements:

* Role-based job system
* Job posting & applications
* TF-IDF based intelligent ranking
* Clean OOP design

---

## ⚠️ Limitations

* Console-based (no GUI)
* Basic text matching
* No database (in-memory only)

---

## 🚀 Future Scope

* Web/GUI interface (React + Spring Boot)
* Database integration (MySQL/PostgreSQL)
* Advanced NLP for better matching
* Email notifications
* Resume parsing (PDF/DOCX)
* Machine Learning improvements

---

## 👨‍💻 Author

**Naman Nayan**
B.Tech CSE

---

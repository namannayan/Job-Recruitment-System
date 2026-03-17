package com.java;
import java.util.*;
class JobSystem {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();
    ArrayList<Application> applications = new ArrayList<>();
    User currentUser = null;
    Scanner sc = new Scanner(System.in);
    int userIdCounter = 1;
    int jobIdCounter = 1;
    void register() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        System.out.print("Role (JobSeeker/Recruiter): ");
        String role = sc.nextLine();
        String resume = "";
        if (role.equalsIgnoreCase("JobSeeker")) {
            System.out.print("Enter Resume Text (include skills): ");
            resume = sc.nextLine();
        }
        User u = new User(userIdCounter++, name, email, pass, role);
        u.resume = resume;
        users.add(u);
        System.out.println("Registration successful");
    }
    
    void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        for (User u : users) {
            if (u.email.equals(email) && u.password.equals(pass)) {
                currentUser = u;
                System.out.println("Welcome " + u.name + " (" + u.role + ")");
                return;
            }
        }
        System.out.println("Invalid credentials");
    }
    
    void postJob() {
        if (currentUser == null || !currentUser.role.equals("Recruiter")) {
            System.out.println("Only recruiter can post jobs");
            return;
        }
        System.out.print("Job Title: ");
        String title = sc.nextLine();
        System.out.print("Location: ");
        String location = sc.nextLine();
        System.out.print("Salary: ");
        int salary = sc.nextInt();
        sc.nextLine();
        System.out.print("Required Skills (comma-separated, e.g., java,python,sql): ");
        String skills = sc.nextLine();
        
        Job j = new Job(jobIdCounter++, title, location, salary, currentUser.id, skills);
        jobs.add(j);
        System.out.println("Job posted successfully with skill requirements");
    }
    
    void viewJobs() {
        if (jobs.isEmpty()) {
            System.out.println("No jobs available");
            return;
        }
        for (Job j : jobs) {
            int count = 0;
            for (Application a : applications) {
                if (a.jobId == j.id) {
                    count++;
                }
            }
            System.out.println("ID: " + j.id + " | " + j.title + " | " + j.location + 
                             " | Salary: " + j.salary + " | Skills: " + j.skills + 
                             " | Applicants: " + count);
        }
    }
    
    void searchJob() {
        System.out.print("Enter keyword: ");
        String key = sc.nextLine().toLowerCase();
        for (Job j : jobs) {
            if (j.title.toLowerCase().contains(key) || 
                j.skills.toLowerCase().contains(key)) {
                System.out.println("ID: " + j.id + " | " + j.title + " | " + j.location + 
                                 " | Salary: " + j.salary + " | Skills: " + j.skills);
            }
        }
    }
    
    void applyJob() {
        if (currentUser == null || !currentUser.role.equals("JobSeeker")) {
            System.out.println("Only job seekers can apply");
            return;
        }
        viewJobs();
        System.out.print("Enter Job ID to apply: ");
        int jobId = sc.nextInt();
        sc.nextLine();
        
        // Check if already applied
        for (Application a : applications) {
            if (a.userId == currentUser.id && a.jobId == jobId) {
                System.out.println("You have already applied for this job!");
                return;
            }
        }
        
        applications.add(new Application(currentUser.id, jobId));
        System.out.println("Application submitted!");
    }
    
    void updateResume() {
        if (currentUser == null || !currentUser.role.equals("JobSeeker")) {
            System.out.println("Only job seekers can update resume");
            return;
        }
        System.out.print("Enter resume text (include skills): ");
        currentUser.resume = sc.nextLine();
        System.out.println("Resume updated");
    }
    
    void viewApplicants() {
        if (currentUser == null || !currentUser.role.equals("Recruiter")) {
            System.out.println("Only recruiter can view applicants");
            return;
        }
        System.out.print("Enter Job ID: ");
        int jobId = sc.nextInt();
        sc.nextLine();
        
        Job job = null;
        for (Job j : jobs) {
            if (j.id == jobId) {
                job = j;
                break;
            }
        }
        
        if (job == null) {
            System.out.println("Job not found");
            return;
        }
        
        boolean found = false;
        for (Application a : applications) {
            if (a.jobId == jobId) {
                for (User u : users) {
                    if (u.id == a.userId) {
                        System.out.println("Applicant: " + u.name + 
                                         " | Email: " + u.email);
                        found = true;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("No applicants yet.");
        }
    }
    
    // Calculate TF-IDF scores for all applicants of a job
    void rankResumesForJob() {
        if (currentUser == null || !currentUser.role.equals("Recruiter")) {
            System.out.println("Only recruiter can rank resumes");
            return;
        }
        
        System.out.print("Enter Job ID to rank applicants: ");
        int jobId = sc.nextInt();
        sc.nextLine();
        
        // Find the job
        Job job = null;
        for (Job j : jobs) {
            if (j.id == jobId) {
                job = j;
                break;
            }
        }
        
        if (job == null) {
            System.out.println("Job not found");
            return;
        }
        
        // Get all applicants for this job
        List<Application> jobApplications = new ArrayList<>();
        for (Application a : applications) {
            if (a.jobId == jobId) {
                jobApplications.add(a);
            }
        }
        
        if (jobApplications.isEmpty()) {
            System.out.println("No applicants for this job yet.");
            return;
        }
        
        // Get all resumes for document frequency calculation
        List<String> allResumes = new ArrayList<>();
        for (User u : users) {
            if (!u.resume.isEmpty()) {
                allResumes.add(u.resume.toLowerCase());
            }
        }
        
        // Calculate TF-IDF for each applicant
        Map<Integer, Double> applicantScores = new HashMap<>();
        
        for (Application app : jobApplications) {
            User applicant = null;
            for (User u : users) {
                if (u.id == app.userId) {
                    applicant = u;
                    break;
                }
            }
            
            if (applicant == null || applicant.resume.isEmpty()) {
                applicantScores.put(app.userId, 0.0);
                continue;
            }
            
            double score = calculateTFIDFScore(applicant.resume, job.requiredSkills, allResumes);
            app.tfIdfScore = score;
            applicantScores.put(app.userId, score);
        }
        
        // Sort applicants by score (descending)
        List<Map.Entry<Integer, Double>> sortedScores = new ArrayList<>(applicantScores.entrySet());
        sortedScores.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        
        // Display ranking
        System.out.println("\n=== Resume Ranking for Job: " + job.title + " ===");
        System.out.println("Required Skills: " + job.skills);
        System.out.println("\nRanking (based on TF-IDF score):");
        System.out.println("----------------------------------------");
        
        int rank = 1;
        for (Map.Entry<Integer, Double> entry : sortedScores) {
            User applicant = null;
            for (User u : users) {
                if (u.id == entry.getKey()) {
                    applicant = u;
                    break;
                }
            }
            
            if (applicant != null) {
                System.out.printf("%d. %s | Email: %s | Score: %.4f\n", 
                                 rank++, applicant.name, applicant.email, entry.getValue());
            }
        }
    }
    
    /**
     * Calculates TF-IDF score for a resume against required skills
     * TF-IDF is always non-negative: TF (0-1) × IDF (≥0) = ≥0
     */
    private double calculateTFIDFScore(String resume, List<String> requiredSkills, List<String> allResumes) {
        // Handle edge cases
        if (resume == null || resume.trim().isEmpty() || allResumes == null || allResumes.isEmpty()) {
            return 0.0;
        }
        
        // Split resume into words
        String[] resumeWords = resume.toLowerCase().split("\\W+");
        if (resumeWords.length == 0) {
            return 0.0;
        }
        
        int totalDocs = allResumes.size();
        double totalScore = 0.0;
        
        // Calculate score for each required skill
        for (String skill : requiredSkills) {
            skill = skill.toLowerCase().trim();
            
            // 1. Calculate Document Frequency (DF) - count resumes containing this skill
            int docFrequency = 0;
            for (String doc : allResumes) {
                // Split document into words for exact matching
                String[] docWords = doc.toLowerCase().split("\\W+");
                for (String word : docWords) {
                    if (word.equals(skill)) {
                        docFrequency++;
                        break; // Count document only once
                    }
                }
            }
            
            // 2. Calculate Term Frequency (TF) in current resume
            int termFrequency = 0;
            for (String word : resumeWords) {
                if (word.equals(skill)) {
                    termFrequency++;
                }
            }
            
            // Only add to score if skill appears in resume
            if (termFrequency > 0) {
                // TF = (times skill appears) / (total words in resume)
                double tf = (double) termFrequency / resumeWords.length;
                
                // FIXED: IDF formula that guarantees non-negative values
                // Adding 1 to both numerator and denominator ensures ratio >= 1
                double idf = Math.log((double) (totalDocs + 1) / (docFrequency + 1));
                
                // Add to total score (now always non-negative)
                totalScore += tf * idf;
            }
        }
        
        // Round to 4 decimal places to avoid floating point precision issues
        return Math.round(totalScore * 10000.0) / 10000.0;
    }
    
    void menu() {
        while (true) {
            if (currentUser == null) {
                System.out.println("\n=== Job Portal ===");
                System.out.println("1 Register");
                System.out.println("2 Login");
                System.out.println("0 Exit");
                System.out.print("Choice: ");
                int ch = sc.nextInt();
                sc.nextLine();
                switch (ch) {
                    case 1:
                        register();
                        break;
                    case 2:
                        login();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } else if (currentUser.role.equals("Recruiter")) {
                System.out.println("\n=== Recruiter Menu ===");
                System.out.println("1 Post Job");
                System.out.println("2 View Jobs");
                System.out.println("3 View Applicants");
                System.out.println("4 Rank Resumes for Job (TF-IDF)");
                System.out.println("5 Logout");
                System.out.print("Choice: ");
                int ch = sc.nextInt();
                sc.nextLine();
                switch (ch) {
                    case 1:
                        postJob();
                        break;
                    case 2:
                        viewJobs();
                        break;
                    case 3:
                        viewApplicants();
                        break;
                    case 4:
                        rankResumesForJob();
                        break;
                    case 5:
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                System.out.println("\n=== Job Seeker Menu ===");
                System.out.println("1 View Jobs");
                System.out.println("2 Search Job");
                System.out.println("3 Apply Job");
                System.out.println("4 Update Resume");
                System.out.println("5 Logout");
                System.out.print("Choice: ");
                int ch = sc.nextInt();
                sc.nextLine();
                switch (ch) {
                    case 1:
                        viewJobs();
                        break;
                    case 2:
                        searchJob();
                        break;
                    case 3:
                        applyJob();
                        break;
                    case 4:
                        updateResume();
                        break;
                    case 5:
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }
}
🧠 AI-Powered Resume Analyzer (Spring Boot)

The AI Resume Analyzer is a Spring Boot application that analyzes resumes uploaded by users (PDF or plain text).
It automatically extracts text, sends it to an AI model (OpenAI or any LLM endpoint), and returns structured insights such as:

Candidate strengths

Missing or suggested skills

Grammar and writing improvements

Suitable job roles + fit score

Summary of the profile

This project is designed for students, job seekers, career portals, and developers who want an AI-powered resume analysis system.

✨ Key Features
📄 Resume Upload & Processing

Accepts PDF and Text files

Uses Apache PDFBox for extracting text

Cleans & formats extracted content

Validates file type and size

🤖 AI-Driven Insights

The AI provides:

Profile summary

Strength analysis (skills, experience indicators, achievements)

Missing or recommended skills

Grammar corrections + clarity suggestions

Job role recommendations with fit score

A confidence score for the analysis

🌐 Interface Options

JSP UI included (simple & quick to use)

Can be replaced with React or any frontend

REST API for integration with third-party apps

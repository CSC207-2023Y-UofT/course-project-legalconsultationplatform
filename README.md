# Legal Consultation Platform+
*Bridging the Gap in Legal Consultation Services*

## Overview

The COVID-19 pandemic has brought numerous challenges to the forefront, one of which is an increased need for free legal consultation services across the United States. The uncertainty and economic instability resulting from the pandemic have led to a surge in legal inquiries, which existing resources have been unable to meet effectively. This discrepancy signals a systemic issue that needs immediate attention.

Despite the American Bar Association's (ABA) best efforts, their data reveals a worrying trend. Across the U.S., over 30% of legal questions posed on their platform remain unanswered by attorneys. In certain states like Louisiana, the neglect is even more pronounced with over 70% of inquiries going unanswered. Additionally, among the questions that do receive attention, approximately 35% are inadequately addressed.

Addressing this gap, we introduce the **Legal Consultation Platform+** , a solution tailored to serve both individuals seeking legal advice and registered, licensed attorneys willing to provide it. Our platform is designed to create an environment where individuals can post their legal inquiries free of charge, and attorneys can browse and respond to these questions at their convenience.

One of the standout features of **Legal Consultation Platform+** is its intelligent attorney-question matching system. This system recommends up to five questions from the feed to each attorney, based on their expertise and interests. This not only streamlines the process for attorneys, saving them valuable time, but also enhances the quality of responses. To ensure attorneys do not miss out on relevant inquiries, the platform also sends them email notifications.

Our mission with **Legal Consultation Platform+** is to democratize access to legal consultation and ensure no question goes unanswered or improperly addressed. Together, we can navigate these challenging times by making legal assistance more accessible and efficient for all.



---
## Installation Guide
[*In Development*]

---
## Client User Guide

### Registration and Account Set-Up

Upon registration, individuals seeking free legal consultation are prompted to set up their accounts and provide demographic information. After registration, clients can log in using their provided user ID and password.

### Interface and Features

The client's homepage presents two main options: initiating a new question or viewing historical queries.

#### Posting a New Question

To post a new question, clients must provide basic information, such as the question title, type, and legal deadline. They can then elaborate on their query in the detailed description section on the specific question page. After submission, clients can track the status of their questions.

#### Tracking Questions

Clients can monitor whether their questions have been answered on the "View Historical Questions" page, which lists all previously asked questions. To enhance visibility, we send an email notification to the client's registered email address once their question has been answered, and we mark the question in the question history page.

#### Closing Questions

Once a client is satisfied with the answer to a question, they can opt to close the question. Closing a question makes it invisible and unresponsive to all attorneys.

#### Rating Experience

Finally, clients can provide binary ratings on their overall experience.

---

## Attorney User Guide

### Registration and Verification

Attorneys interested in participating on our platform should first contact us via email for verification of confidentiality and personal information. After registration, they can login using their user ID and password.

### Interface and Features

The attorney's homepage allows them to browse all available questions or track previously answered questions to handle any follow-up queries from clients.

#### Browsing and Answering Questions

On the "Browse Questions" page, attorneys can view all questions that have not yet been claimed. After selecting a question of interest, they can view its details and provide an answer. Once an attorney has answered a question, they are expected to fully dedicate themselves to that question, taking care of any follow-up queries. We provide email notifications for any follow-up questions.

#### Question Recommendation and Notification

Attorneys also receive a weekly email containing a feed of recommended questions, based on our intelligent matching algorithm. They can view their question feed by clicking the "Question Recommendations" button on their homepage.

---

## Architecture Overview

### User Request Flow

![Packaging Strategy](["https://github.com/CSC207-2023Y-UofT/course-project-legalconsultationplatform/assets/123603507/b043145a-2765-428d-8f04-49d1799400ca"](https://user-images.githubusercontent.com/123603507/260920851-b043145a-2765-428d-8f04-49d1799400ca.PNG))

Our application's design and packaging strategy rigorously adheres to the principles of Clean Architecture, ensuring a clear separation of concerns and a maintainable structure. Here's a breakdown of how user requests navigate through our system:

1. **Request Initiation**: Users engage with the system via the UI.
2. **Data Bundling**: The Controller captures the user's input, crafting it into a standardized 'request model'.
3. **Use Case Processing**: The bundled input is directly passed to the appropriate use case or interactor by the Controller. Here, operating within the confines of our business rules, it processes the input. All interactions, whether storing new data or fetching existing ones, are channeled through a 'gateway' interface, ensuring modularity and a clear distinction between business logic and data access.
4. **Response Structuring**: Post-processing, the use case returns a response, tailored in a format that the Presenter can easily interpret and utilize.
5. **UI Update**: With the processed response in hand, the Presenter manages the presentation logic, ensuring that the UI is updated seamlessly to either showcase results or reflect necessary changes.


### Design Patterns


#### 1. Dependency Injection
Our use of the dependency injection principle is especially pronounced in the database interfaces and output boundaries. Through this principle, we inject such dependencies as the output boundary and gateway into our use cases. This approach ensures our use case development remains isolated from the concrete implementations, thus enhancing modularity and maintainability.

#### 2. Factory Design Pattern
The Factory design pattern, evidenced in components like `UserGatewayFactory` and `UserFactory`, empowers our system to cater to varying use-case requirements by dynamically instantiating the appropriate objects. This approach ensures our software can adapt to new requirements or changes without disrupting existing functionalities.

#### 3. Builder Design Pattern

Our user registration process prominently showcases the application of the Builder design pattern. This pattern facilitates the step-by-step construction of complex objects, such as our `Client` and `Attorney` entities. By employing the Builder, our system becomes inherently adaptable to future field additions, negating the need for significant changes to existing factory classes. Moreover, it endows classes like `Attorney` and `Client` with the flexibility to introduce distinct fields as needed.

#### 4. Strategy Design Pattern
The question viewing mechanism in our system showcases the strategy design pattern. The `ViewQuestionInteractorBase` class offers a foundational blueprint for viewing questions. However, the specifics—fetching questions list and user details—are abstracted and variably implemented in derived classes like `BrowseQuestionInteractor`. This encapsulation of specific strategies enhances our flexibility to new features with similar requirements.

#### 5. Hybrid MVP and MVC Design Pattern
Our software adopts a hybrid Model-View-Controller (MVC) and Model-View-Presenter (MVP) design approach. While the Model, represented by interactors, manages data and business logic, the View remains a passive display component. The Controller processes user inputs, and in a nuanced departure from traditional patterns, the Presenter is dedicated to output formatting and display. This blend of MVC and MVP principles enhances modularity, isolates UI changes from business logic, and optimizes testability.

---
## Database Specification
[*In Deevelopment*]

---
## Matching System Specification

Our system is designed to enhance attorney-client interactions by intelligently pairing attorneys with relevant questions.
Attorneys are matched with up to five unanswered questions weekly, ensuring consistent engagement and timely responses.

### System Design

The attorney-question relationship is visualized as a bipartite graph. Each edge symbolizes potential interactions between attorneys and clients, with distinct weights denoting the relevance of the match.
To find the optimal pairings, we employ the Hungarian algorithm, maximizing the cumulative edge weights.

### Weight Calculation

The weight for a match between client i, question j, and attorney k is computed as:

### Predictive Modeling

The probability of client satisfaction is derived from a Random Forest classifier. This model is trained on a rich dataset that incorporates client demographics, question language attributes, and various attorney attributes.



---

## Team Members and Roles

### Yifan Liu - Project Manager & Machine Learning Specialist
Yifan directs project coordination, ensuring alignment among different developers, and meticulously tracks progress to ensure project milestones are met on time. As a Machine Learning Specialist, he has spearheaded the development of our proprietary question-attorney matching system, leveraging advanced Machine Learning and Natural Language Processing techniques.

### Zihan Yuan - User Interface Designer & Front-End Developer
Zihan is tasked with the design and development of the user-centric front-end interface of our platform. Her contribution to the team extends to the management of the user registration and login system, ensuring a seamless user experience.

### Cheng Peng - Software Verification Specialist & Back-End Developer
As a Software Verification Specialist, Cheng plays a pivotal role in ensuring the functionality and reliability of all platform features through rigorous testing and coordination. Additionally, he oversees the implementation of the question-asking and reply features as a Back-End Developer.

### Zhouyi Yu - Data Engineer
Zhouyi is the backbone of our data infrastructure. As our Data Engineer, he is responsible for the design, development, and maintenance of the robust database system that powers our platform.

### Xingfu Wu - Software Verification Specialist & IT Support Specialist
Xingfu oversees the testing of higher-level entities in the platform, ensuring our product meets stringent quality standards. He also doubles as our IT Support Specialist, proficiently handling server setup and maintenance.

### River Qi - Back-End Developer
River Qi, as a Back-End Developer, spearheads the 'View Question' feature of our platform. His efforts have been instrumental in ensuring that our users can easily access and navigate their queries.


---
## Future Enhancements

### Web-based Database Management System
In future iterations of our platform, we plan to transition to a web-based database management system. This change will allow for more efficient data management and improved accessibility, making it easier to handle and process data on the platform.

### Intelligent Sample Responses
Another enhancement in our roadmap involves providing intelligent sample responses to clients. This feature will utilize advanced algorithms to identify when a user's query is similar to existing ones. In such instances, the system will automatically suggest sample answers from those previous, similar questions. This will not only speed up the response time for users but also enrich the overall user experience by instantly providing them with helpful information.

### Domain-Specific AI for Timely Responses
We are also in the process of developing a domain-specific Artificial Intelligencesystem. This AI system will be designed to provide timely responses to user queries while we continue to retain the element of real-person answers. This innovative feature will enhance the speed and efficiency of our platform, ensuring that users get the information they need as quickly as possible.


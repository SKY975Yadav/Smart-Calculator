# Smart Calculator

A simple calculator application with a graphical user interface (GUI) built using JavaFX. This project supports basic arithmetic operations and evaluates mathematical expressions using postfix notation.

## Project Overview
The Smart Calculator is designed to provide an intuitive way for users to perform mathematical calculations. Unlike traditional calculators, this application processes mathematical expressions dynamically and showcases the internal workings of postfix evaluation, making it an excellent learning tool for students and developers alike.

## Features

- User-friendly graphical interface.
- Supports basic arithmetic operations: addition, subtraction, multiplication, division, and modulus.
- Converts infix expressions to postfix and evaluates them accurately.
- Handles invalid input and division by zero gracefully.

## Screenshots

*(Include screenshots of the application interface here if possible)
![image](https://github.com/user-attachments/assets/14d082d8-8a10-4474-91e7-35774e6f5064)


## Technologies Used

- **Programming Language:** Java
- **Framework:** JavaFX
- **Development Environment:** IntelliJ IDEA

## Installation Instructions

1. **Download and Install JavaFX SDK**:
   - Download the JavaFX SDK from [here](https://gluonhq.com/products/javafx/).
   - Extract it to a directory (e.g., `C:\javafx-sdk`).

2. **Set Up Environment Variables**:
   - Add the JavaFX `lib` directory to your environment variables if required.

3. **Run the Project**:
   - Open the project in IntelliJ IDEA.
   - Configure the VM options in IntelliJ:
     ```
     --module-path /path-to-javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
     ```
   - Build and run the project.

## Setup and Usage

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/SmartCalculator.git
   cd SmartCalculator

## How it works : 
Infix to Postfix Conversion:

Uses the EvaluationOfPostfix class to convert infix expressions (e.g., 3 + 4 * 2) into postfix notation (e.g., 3 4 2 * +).
Postfix Evaluation:

The EvaluationOfPostfix class evaluates the postfix expression using a stack-based approach.
GUI Interaction:

## Learning Outcomes

By exploring this project, developers can learn:
- The fundamentals of postfix expression evaluation.
- The use of JavaFX for creating GUIs.
- Handling exceptions in Java for invalid inputs.
- Integrating backend logic with a front-end interface.

## Contributing

Contributions are welcome! Here's how you can help:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add feature'`).
4. Push the branch (`git push origin feature-name`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Future Enhancements

- Add support for scientific functions (e.g., sine, cosine, logarithm).
- Implement a history feature to track previous calculations.
- Enhance the UI with themes and customization options.
- Add multilingual support for global users.


Users can input expressions through the on-screen buttons.
The application dynamically updates the display and evaluates expressions when the = button is pressed.

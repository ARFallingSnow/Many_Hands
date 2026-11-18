# User Interface Suite
### Author: Alex, R
### Purpose: Provides a collection of custom objects to allow user interaction with custom projects and utilities 
### Disclaimer: Copilot Artificial intelligence was used in the creation of this code for troubleshooting and generalize design directions. The objective of this code is to practice java 2d development not for commercial distribution. All ideas, assets, and code was created by the author. 
---
## Process Overview: 

### Key Input: 
    <ul>
    - Core instantiates interface manager and loop objects 
    - Interface manager creates the window, key handler, and input manager at instantiation
    - Window is passed the key handler object and has it set as its AWT keyListener object 
    - Window attribute focusable is set true which allows the window to be selected as the 
      focus of user interaction. This is essential for the key listener to function 
    - Key handler is added to the window as the key listener
    - Key handler passively listens for a key press or key release event while the window is in focus
    - Each key event changes the state of each associated boolean 
    - Interface manager receives an update tick from the core at regular intervals triggered by the 
      loops running thread >> loop.update() >> Core.updateTick(int time) >> interfaceManager.updateTick(int time) >> inputHandler.updateInputs
    - Each update tick prompts the interface manager to check key status through the input handler object
    - Input handler, when prompted, checks the status of the key handlers bools for each associated key 
    - Any keys pressed at time of prompt will be considered in the input handlers internal debounce logic 
    - If a key bool is true the associated lock bool is checked. If lock is false then an action tick is triggered.
    - The associated lock bool is set true. If the key bool is true next cycle and lock bool is still true then an action tick cannot be triggered. This mechanism prevents they key from being erroneously read multiple times.
    - The lock bool is held until the key released and the key handler is checked to find the associated key bool is false
    - InputHandler.actionTick(String cmd) calls interfaceManager.keyUpdate(String cmd) with the cmd based on which key is pressed
    - InterfaceManager.keyUpdate(String cmd) calls core.passKey(String key) for use elsewhere in the program
    </ul> 

---
## Essential Objects:  

### Interface Manager: 

### Window: 

### Input Handler: 

### Key Handler: 

--- 
## Requirements: 

    - Logic:
        - Core
        - Manager
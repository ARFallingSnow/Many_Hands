# Logic suite
### purpose: provide standardized code base for running various java projects
### Author: Alex R
### Disclaimer: Copilot Artificial intelligence was used in the creation of this code for troubleshooting and generalize design directions. All ideas, assets, and code was created by the author. 

## Classes:
<UL>
    - Core: Primary object for coordinating and controlling all child manager objects. Provides time dissemination to objects that need it 
    - Launcher: Initial program in the kill chain. Starts the core object or other main objects based on gameMode set 
    - Manager: Abstract Class to pattern all manager objects 
    - Loop: Thread running object used to provide gameTick timing for other objects. Updates core directly with each cycle

</UL>

## Process Overview: 

 #### Timing: 

 #### Display Output: 

 #### User Input: 
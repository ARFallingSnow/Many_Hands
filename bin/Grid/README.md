# Grid Suite
### purpose: Provide navigable 2d map objects using a 2d int matrix and supporting functions      
### Author: Alex R
### Disclaimer: Copilot Artificial intelligence was used in the creation of this code for troubleshooting and generalize design directions. The objective of this code is to practice java 2d development not for commercial distribution. All ideas, assets, and code was created by the author. 

---
## Objects
---
### Grid
    primary object acted upon for the suite. Contains a multidimensional integer array containing integer information at a specific intersection of x position and y position. Allowing a 2 dimensional map where each integer present at their respective coordinates can be linked to specific attributes based on its value. 

### GridHandler 
    primary interface used by the Logic and UI suite for operations built around the grid object

### Generator
    able to create and modify the gird object based on givin parameters

===
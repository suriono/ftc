Load my version "MGM2RevE-Uz.ini" config file Slic3r and generate a gcode.


Center the top nozzle.


Use Repetier:

  Run the gcode, it will bring the bed to the bottom.
  
  Manually raise the bed until it clicks, until the USB is blinking again.
  
  Manually set the temperature to 190C and wait until it hits 190C.
  
  Comment the gcode near the top : 
  
    ;G28
    
  Comment the gcode the following lines starting from around line 25:
  
    ;G90 ; use absolute coordinates
    
    ;M82 ; use absolute distances for extrusion
    
    ;G92 E0
    
    ;G1 Z0.350 F7800.000
    
    ;G1 E-2.00000 F2400.00000
    
    ;G92 E0
    
    ;G1 X-45.842 Y2.257 F7800.000
    
    ;G1 E2.00000 F2400.00000

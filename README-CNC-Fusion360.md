Edit the following lines from a *.tap gcode:
    comment the following line because it causes the CNC to go lower initially
        ;G28 G91 Z0.
    add the following line below it to allow initial clearance
        Z5

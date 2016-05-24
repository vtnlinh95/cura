# Cura

## Summary

Cura is a match-making application to connect patients and doctors.

## Architecture

Cura use basic MVC architecture to handle data flow

<img src="https://github.com/hieumai/cura/blob/master/docs/images/architecture.png" alt="Diagram"/>

### View
- Activity or Fragment
- Process UI Events and call Controllers for help when needed

### Controller
- Process request from Views
- Get data from Models to return to Views

### Model
- Handle all data loading and validations
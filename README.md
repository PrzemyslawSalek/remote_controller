# Remote Controller

An Android application for building and using custom remote controllers over Bluetooth. Users design their own control layouts by adding and configuring interactive components, then connect to an Arduino-based device and control it in real time.

> Group project — 2020/2021

---

## Features

- **Custom layouts** — add and configure components on a canvas in Edit Mode
- **Multiple component types:**
  - **Button** — sends a configurable string on tap
  - **Slider** — sends a continuous value
  - **LED** — receives toggle/on/off/color commands from the device
  - **Text Area** — receives write/append/clear commands from the device
- **Bluetooth communication** — connects to paired devices over RFCOMM (classic Bluetooth)
- **Persistent storage** — controllers and layouts are saved locally with SQLite + OrmLite and serialized with Jackson
- **Two modes** — Edit Mode for building layouts, Usage Mode for real-time control

## Architecture

```
MainActivity
├── Opening fragment       — list of saved controllers
├── EditMode fragment      — canvas editor (add, configure components)
│   ├── ComponentSelection — pick a component type to add
│   └── *Options fragments — per-component configuration (ButtonOptions, SliderOptions, …)
└── UsageMode fragment     — live controller view connected via Bluetooth
    └── Settings           — select Bluetooth device
```

**Layers:**

| Layer | Classes |
|---|---|
| UI components | `Button`, `SeekBar`, `LED`, `TextArea`, `LEDComponent`, `MyConstrainLayout` |
| Domain model | `Controller`, `Component`, `InputComponent`, `OutputComponent` |
| Bluetooth | `BluetoothService`, `ConnectedThread` |
| Persistence | `DatabaseHelper`, `SerializedControllers` |

## Communication Protocol

The app uses a custom text-based protocol (RFC 10202) over the Bluetooth serial channel.

**Request format (Android → Device):**
```
ID;METHOD_ID;arg1,arg2,...\n
```

**Input components (Device → Android):**

| Component | Commands |
|---|---|
| LED | `ID;toggle\n` · `ID;on\n` · `ID;off\n` · `ID;color;#RRGGBB\n` |
| TextArea | `ID;write;text\n` · `ID;append;text\n` · `ID;clear\n` |

**Output components (Android → Device):**

| Component | Data |
|---|---|
| Button | Sends the user-configured string on click |
| Slider | Sends the current slider value |

## Tech Stack

| | |
|---|---|
| Language | Java |
| Platform | Android (minSdk 22, targetSdk 30) |
| IDE | Android Studio |
| Database | SQLite via OrmLite 5.3 |
| Serialization | Jackson 2.12 |
| Navigation | AndroidX Navigation Component |
| Hardware | Arduino with Bluetooth module (HC-05 / HC-06) |
| UML | StarUML |

## Build

1. Open the `app/` directory in Android Studio.
2. Connect a physical Android device (Bluetooth is not available on emulators).
3. Run the app with **Run → Run 'app'**.

> Requires Android 5.1 (API 22) or higher.

## Documentation

- [Design document](doc/final/Design%20document.pdf)
- [Database schema](doc/final/Database%20schema.pdf)
- [Diagrams document](doc/final/Diagrams%20document.pdf) · [Class diagram](doc/final/NewDiagramClasses.jpg)
- [MoSCoW](doc/final/MoSCoW.pdf)
- [Remote protocol](remote_protocol)
- [Test scenarios](https://docs.google.com/spreadsheets/d/16EjRT1l22sCxBDKyGIyB2vIYR4lbtyRaIoxJBE9BvMo/edit#gid=0)

## Contributors

<table>
  <tr>
    <td align="center">
      <b>Project leader</b><br><br>
      <a href="https://github.com/szymix1999"><img src="https://avatars.githubusercontent.com/u/66270215?v=4" width="100px;" alt=""/><br><sub><b>Szymon Sala</b></sub></a>
    </td>
    <td align="center">
      <b>Front-end engineer</b><br><br>
      <a href="https://github.com/PrzemyslawSalek"><img src="https://avatars.githubusercontent.com/u/66259490?v=4" width="100px;" alt=""/><br><sub><b>Przemysław Sałek</b></sub></a>
    </td>
    <td align="center">
      <b>Back-end engineer</b><br><br>
      <a href="https://github.com/PituchaAleksander"><img src="https://avatars.githubusercontent.com/u/63605795?v=4" width="100px;" alt=""/><br><sub><b>Aleksander Pitucha</b></sub></a>
    </td>
    <td align="center">
      <b>Front-end engineer</b><br><br>
      <a href="https://github.com/kazdyrkeicam"><img src="https://avatars.githubusercontent.com/u/45139068?v=4" width="100px;" alt=""/><br><sub><b>Maciej Rydzak</b></sub></a>
    </td>
    <td align="center">
      <b>Tester</b><br><br>
      <a href="https://github.com/janjanek"><img src="https://avatars.githubusercontent.com/u/63151352?v=4" width="100px;" alt=""/><br><sub><b>Jan Starosta</b></sub></a>
    </td>
  </tr>
</table>

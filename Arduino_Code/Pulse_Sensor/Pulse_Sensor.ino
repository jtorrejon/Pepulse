//Author: Pepe Torrejón Rodríguez
//FYP: Health Wristband

int pulsePin = 0;                 // Pulse Sensor purple wire - A0
int blinkPin = 13;                // Pin to blink LED at each heart beat
int fadePin = 5;                  // Pin to do some fade at each heart beat
int fadeRate = 0;                 // Faderate for LEDs


volatile int BPM;                   // Store the pulse rate
volatile int Signal;                // Store the raw data
volatile int IBI = 600;             // Store the time between two beats
volatile boolean signal_flag = false;        // If there's heart beat, true; false if it's not.
volatile boolean Pulse = false;
char blueToothVal;
char lastValue;


void setup(){
  pinMode(blinkPin,OUTPUT);         
  pinMode(fadePin,OUTPUT);          
  Serial.begin(9600);             // Serial communication begins
  interruptSetup();                 // sets up to read Pulse Sensor signal every 2mS  
}

void loop(){

  //Send 'Signal' to any Android Device connected via Bluetooth
  if(signal_flag == true){
  
     fadeRate = 255; // Set 'fadeRate' Variable to 255 to fade LED with pulse
     if(Serial.available()){blueToothVal = Serial.read();} //If there's any Serial device connected, read the Serial flow
   
	Serial.print("#");
        Serial.print(BPM); //Print the pulse rate
	Serial.print("~");
    
     signal_flag = false;//Reset the flag
  }
  ledFadeToBeat(); 
  delay(20);                             //Just to take some time between each heart beat
}


void ledFadeToBeat(){
    fadeRate -= 15;                         //  Set the fade value for LEDs
    fadeRate = constrain(fadeRate,0,255);   //  Constrain makes any number to be in that value range
    analogWrite(fadePin,fadeRate);          //  Blink Led
  }


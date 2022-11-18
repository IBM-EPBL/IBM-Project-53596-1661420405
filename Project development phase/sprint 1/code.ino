#include <time.h>

bool exhaust_fan_on = false;
bool sprinkler_on = false;

float temperature  = 0;
int gas = 0;
int flame = 0;

String flame_status = "";
String accident_status = "";
String sprinkler_status = "";

void setup() {
    Serial.begin(99900);
}

void loop() {

    //setting a random seed

    srand(time(0));

    //initial variable

    temperature = random(-20,125);
    gas = random(0,1000);
    int flamereading = random(200,1024);
    flame = map(flamereading,0,1024,0,2);

    //set a flame status

    switch (flame) {
    case 0:
        flame_status = "No Fire";
        Serial.println("Flame Status : "+flame_status);
        break;
    case 1: 
        flame_status = "Fire is Detected";
        Serial.println("Flame Status : "+flame_status);
        break;
    }

    //Gas Detection

    if(gas > 100){
        Serial.println("Gas Status : Gas leakage Detected");
    }
    else{
        exhaust_fan_on = false;
        Serial.println("Gas Status : No Gas leakage Detected");
    }


    //send the sprinkler status
    if(flame){
        sprinkler_status = "working";
        Serial.println("Sprinkler Status : "+sprinkler_status);
    }
    else{
        sprinkler_status = "not working";
        Serial.println("Sprinkler Status : "+sprinkler_status);
    }

    //toggle the fan according to gas

    if(gas > 100){
        exhaust_fan_on = true;
        Serial.println("Exhaust fan Status : Working");
    }
    else{
        exhaust_fan_on = false;
        Serial.println("Exhaust fan Status : Not Working");
    }

    Serial.println("");
    Serial.println("");
    Serial.println(" -------------------------*************---------------------");
    Serial.println("");
    Serial.println("");


    delay(3000);


}

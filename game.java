void setup() {
  size(800, 800);
    
}

int chopperBladeLeft = #805349;
int chopperBladeRight = #805349;

int bg = #33ccff;

int backBladeTop = #006600;
int backBladeButtom = #006600;

int backBladeLeft =  0;
int backBladeRight =  0;

float g = 9.8;
boolean start = false;

float topBladeY = 610;
float bladePipeY = 612;
float bodyY = 630;
float windowY = 632;
float bladePipe2Y = 632;
float leftTopBladeY = 632;
float rightTopBladeY = 632;

float backTopBlade = 625;
float backButtomBlade = 635;

float gTororance = 3.5;

boolean limitExceeded  = false;

int[] topColor = {#99ff66, #cc99ff, #3399ff, #ccff33, #33cc33};
int topDefaultColor = #99ff66;

int[] bottomColor = {#003399, #006600, #ff6600, #cc66ff};
int bottomDefaultColor = #339966;
int oX = 800;
int oY = 300;
int bX = 800;
int bY = 300;



void gefroceOnChopper(float impactOfGravity) {
     if (!limitExceeded && start == true) {
      topBladeY += impactOfGravity;
      bladePipeY += impactOfGravity;
      bodyY += impactOfGravity;
      windowY += impactOfGravity;
      bladePipe2Y += impactOfGravity;
      leftTopBladeY += impactOfGravity;
      rightTopBladeY += impactOfGravity;
      backTopBlade += impactOfGravity;
      backButtomBlade += impactOfGravity;
      
   
      
     
      int limitY = (int) bodyY;
      int limitNegativeY = (int) topBladeY;
      
      if (limitY >= 775 || limitNegativeY <= 10) {
        limitExceeded = true;
        start = false;
        gameOver = true;
      }
   
      
     }
}

boolean againstG = false;
int motionVectorNegativeY = 0;

float motionEffectNegativeY = 0.5;
boolean keyRelease = false;

boolean negativeVectorY = true;
boolean yVector = false;
boolean gameOver = false;
int socre = 0;
int beaconLight = #ff0000;

void character() {
   noStroke();
  fill(chopperBladeLeft);
  ///    x   y   size
  rect(40, topBladeY, 35, 5);   // blade left
  fill(chopperBladeRight);
  rect(60, topBladeY, 30, 5);  // blade right
  fill(0);
  rect(65, bladePipeY, 5, 20);
  fill(#53612D);
  rect(40, bodyY, 50, 25, 8.5); // body
  
  fill(beaconLight); // Beacon 
  circle(90, bodyY + 25, 10);
  
  fill(0);
  circle(85, windowY + 2, 10); // window
  
  fill(#660066);
  rect(20, bladePipe2Y, 25, 5); // bladePipeH
  // back blade top
   fill(backBladeTop);
  rect(20, backTopBlade, 5, 10);
  fill(backBladeButtom);
  // back blade button
   rect(20, backButtomBlade, 5, 10);
   
   
   fill(backBladeLeft);
   rect(10, leftTopBladeY, 10, 5);
   
   fill(backBladeRight);
   rect(25, rightTopBladeY, 10, 5);
 
}

void chopperRotation () {
  if (frameCount % 2 == 0 && start == true){
    if(beaconLight != 0 && frameCount % 16 == 0) {
      beaconLight = #ff0000;
    }else{
      beaconLight = bg;
    }
        // main blade animation
        if (chopperBladeRight == #805349) {
            chopperBladeRight = bg;
            chopperBladeLeft = #805349;
        }else{
        chopperBladeLeft = bg;
        chopperBladeRight = #805349;
        }
        // back blade animation 
        if (backBladeTop == 0) {
          backBladeTop = bg;
          backBladeButtom =  0;
          backBladeLeft = 0;
          backBladeRight = bg;
        }else{
          backBladeButtom  = bg;
          backBladeTop = 0;
          backBladeLeft = bg;
          backBladeRight = 0;
        }
  }
}


void draw() {
   background(bg);
   fill(bg);
   rect(0, 700, 800, 200);
   
     fill(topDefaultColor);
     rect(oX, 0, 100, oY);
     fill(bottomDefaultColor);
     //   x    y    h    w
     rect(bX, 500, 100, bY);
   
    character();
    
    if (keyCode == 10 && gameOver == false){
      chopperRotation();
      start = true;    
    }
    
    if (gameOver) {
      textAlign(CENTER);
      fill(153, 102, 255);
      textSize(30);
      text("GAME OVER, SCORE COUNT " + socre, 10, 10, width - 20, 50);
    }else {
       textAlign(RIGHT);
      fill(#ff3300);
      textSize(30);
      text("Score count " + socre, 10, 10, width - 20, 50);
    
    }
  
   if (start) {
      if (frameCount % 16 == 0) {
        socre++;
      }
        // render obstacles 
       int widthTop = (int)random(200, 400);
       int widthBottom = (int)random(250, 400);
       
       if (frameCount % 2 == 0 && start == true){
         if (oX <= -100) {
            int colorTop = (int)random(0, topColor.length);
           topDefaultColor = topColor[colorTop];
           oX = 800;
           oY = widthTop;
         }
        if (bX <= -100) {
          int colorBottom = (int)random(0, bottomColor.length);
          bottomDefaultColor = bottomColor[colorBottom];
          bX = 800;
          bY = widthBottom;
        }
          // static touch point coordinate in y axias since, moving in x axis is illusion 
          if (bX <=  105 && (int)bodyY >= 500 ) {
              start = false;
              gameOver = true;
          }
          if (oX <= 90 && (int)bodyY <= oY) {
            System.out.println((int) bodyY + " " + widthTop);
             start = false;
             gameOver = true;
          }
             // blade touc top
          int blade = (int)topBladeY;
          System.out.println(blade);
          
          if (oX <= 90 && blade <= oY){
            start = false;
            gameOver = true;
          }
         oX -= 30;  
         bX -= 30;
       }
       
        
     if (!againstG && negativeVectorY){
     gefroceOnChopper(gTororance);
     }
   } 
   if (keyRelease) {
     gefroceOnChopper(+gTororance);
   }
  chopperRotation();
  frameNegativeY();
  if (limitExceeded){chopperRotation();}
}


void frameNegativeY() {
  if (frameCount % 2 == 0 && yVector) {
    
   gefroceOnChopper(-motionVectorNegativeY);
  }

}


void keyPressed() {
  if (keyCode == 38){    
    gefroceOnChopper(0);
    negativeVectorY = false;
     motionVectorNegativeY += 30;
    yVector = true;
  }
  
  if (keyCode == 10 && gameOver == true){
       start = false;
       topBladeY = 610;
       bladePipeY = 612;
       bodyY = 630;
       windowY = 632;
       bladePipe2Y = 632;
       leftTopBladeY = 632;
       rightTopBladeY = 632;
       backTopBlade = 625;
       backButtomBlade = 635;
       gTororance = 3.5;
       limitExceeded  = false;
       gameOver = false;
         oX = 800;
         oY = 300;
         bX = 800;
         bY = 300;
         socre = 0;

  }
  
  
  if (keyCode == 10 && limitExceeded) {
    character();
  }
}

void keyReleased() {
  // wait for the stack to build up
  motionVectorNegativeY = 0;
  keyRelease = true;
}
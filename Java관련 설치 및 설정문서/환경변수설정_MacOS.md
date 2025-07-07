## MacOS에서 JAVA_HOME 환경 변수를 추가하는 방법
  1. JDK설치가 끝나면, 사용자의 홈에 있는 .bash_profile을 TextEdit으로 열고 
  아래의 두 줄을 파일의 맨 아래에 복사해서 붙여넣는다.

        export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home  
        export PATH=$JAVA_HOME/bin:${PATH}

  2. 터미널을 열고 아래의 명령을 실행해서 변경된 내용을 반영한다.  
  source ~/.bash_profile

  3. 마지막으로 jshell명령을 실행해서 설정이 잘반영되었는지 확인한다.     
     % jshell  
       |  Welcome to JShell -- Version 21.0.6  
       |  For an introduction type: /help intro  

       jshell>


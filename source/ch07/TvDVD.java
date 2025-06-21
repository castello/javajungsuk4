class Tv2 {
    boolean power; 	// 전원상태(on/off)
    int channel;		// 채널

    void power()       { power = !power; }
    void channelUp()   { ++channel; }
    void channelDown() { --channel; }
}

class DVD {
    boolean power; 	// 전원상태(on/off)
    int counter = 0;
    void power(){ power = !power; }
    void play() { /* 내용생략*/ }
    void stop() { /* 내용생략*/ }
    void rew()  { /* 내용생략*/ }
    void ff()   { /* 내용생략*/ }
}

class TvDVD extends Tv2 {
    DVD dvd = new DVD();

    void play() {
        dvd.play();
    }

    void stop() {
        dvd.stop();
    }

    void rew() {
        dvd.rew();
    }

    void ff() {
        dvd.ff();
    }
}
#pragma once

#include <stdexcept>
#include <iostream>
#include <cstdlib>
#include <ctime>

class Bomb {
private:
    double effect;

public:
    static void staticInit();
    Bomb();
    void fire();
};

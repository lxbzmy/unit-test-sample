#pragma once

#include "Bomb.hpp"

class Tick {
private:
    Bomb bomb;
    int sum;

public:
    Tick(int sum);
    bool tick();
};

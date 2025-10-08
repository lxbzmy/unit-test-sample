#include "Tick.hpp"

Tick::Tick(int sum) : sum(sum) {}

bool Tick::tick() {
    sum--;
    if (sum <= 0) {
        bomb.fire();
        return false;
    }
    return true;
}

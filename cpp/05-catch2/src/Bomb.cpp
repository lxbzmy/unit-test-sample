#include "Bomb.hpp"

void Bomb::staticInit() {
    std::cout << "You Will Have A Bomb!" << std::endl;
}

Bomb::Bomb() {
    std::cout << "A bomb loaded." << std::endl;
    std::srand(std::time(nullptr));
    // if (static_cast<double>(std::rand()) / RAND_MAX > 0.20) {
    //     throw std::runtime_error("Bomb Exploed.");
    // }
}

void Bomb::fire() {
    throw std::runtime_error("Bomb Fired.");
}

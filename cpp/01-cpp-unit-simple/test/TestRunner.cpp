#include <cppunit/extensions/TestFactoryRegistry.h>
#include <cppunit/ui/text/TestRunner.h>
#include <cppunit/XmlOutputter.h>

#include <fstream>
#include <string>

int main(int argc, char* argv[]) {
    CppUnit::TextUi::TestRunner runner;
    CppUnit::TestFactoryRegistry &registry = CppUnit::TestFactoryRegistry::getRegistry();
    runner.addTest(registry.makeTest());

    std::string xmlPath;
    for (int i = 1; i < argc; ++i) {
        std::string arg = argv[i];
        if (arg == "--xml" && i + 1 < argc) {
            xmlPath = argv[++i];
        } else if (arg.rfind("--xml=", 0) == 0) {
            xmlPath = arg.substr(6);
        }
    }

    std::ofstream xmlFile;
    if (!xmlPath.empty()) {
        xmlFile.open(xmlPath);
        if (xmlFile.is_open()) {
            runner.setOutputter(new CppUnit::XmlOutputter(&runner.result(), xmlFile));
        }
    }

    bool wasSuccessful = runner.run("", false);

    if (xmlFile.is_open()) {
        xmlFile.close();
    }

    return wasSuccessful ? 0 : 1;
}
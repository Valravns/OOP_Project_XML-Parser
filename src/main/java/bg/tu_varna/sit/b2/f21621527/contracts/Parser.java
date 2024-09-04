package bg.tu_varna.sit.b2.f21621527.contracts;

import bg.tu_varna.sit.b2.f21621527.models.XMLDocument;

import java.io.File;

public interface Parser {
    XMLDocument parse(File file);
}

# FileDirectory-Tracker

How   to   run:
1.   Unzip   archive
2.   Enter   directory
3.  Run   mvn   clean   install   exec:java
What   was   done:
1.   All   files   have   a   single   responsibility   and   conform   SOLID   principle
-   DirectoryReaderUtil   -   main   class;
-   CollectingFileVisitor   -   traverses   directory   recursively   and   collect   what   was   visited   into   a   map -   Converter   -   converts   data;
-   AbstractResource,   Directory,   Document   -   data   files   which   follow   OOP   principles. AbstractResource   is   a   common   type,   Directory   and   Document   are   specific   implementations
2.   Unit   tests   added.   Coverage   is   more   than   79%
3.   PMD,   Checkstyle   and   Findbugs   plugins   were   added   in   order   to   enforce   code   quality   via   static
code   analysis
4.   JaCoCo   plugin   added   to   enforce   unit   tests   coverage

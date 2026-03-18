#!/bin/bash

# Library Catalog System - Compilation and Run Script

echo "=== LIBRARY CATALOG SYSTEM ==="
echo "Compiling Java files..."

# Compile all Java files
javac *.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Choose an option:"
    echo "1. Run interactive application (LibraryCatalogSystem)"
    echo "2. Run demo (LibraryCatalogDemo)"
    echo "3. Exit"
    echo ""
    read -p "Enter your choice (1-3): " choice
    
    case $choice in
        1)
            echo "Starting interactive application..."
            java LibraryCatalogSystem
            ;;
        2)
            echo "Starting demo..."
            java LibraryCatalogDemo
            ;;
        3)
            echo "Exiting..."
            exit 0
            ;;
        *)
            echo "Invalid choice. Running demo by default..."
            java LibraryCatalogDemo
            ;;
    esac
else
    echo "Compilation failed. Please check for errors."
    exit 1
fi


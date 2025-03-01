<div align="center">

<sup>Special thanks my followers for supporting me:</sup>

<div>
<img src="./logo.png" width="230" alt="Warp" />
</div>
<b>
VDF4J A Verifiable Delay Function (VDF) is a function that requires substantial time to evaluate 
(even with a polynomial number of parallel processors) but can be very quickly verified as correct. 
VDFs can be used to construct randomness beacons with multiple applications in a blockchain
network environment.
</b>
<div>
<sup>Visit <u>warp.dev</u> to learn more.</sup>
</div>


<hr />

# VDF4J

<a href="">![build-status](https://ci.appveyor.com/api/projects/status/github/PanagiotisDrakatos/VDF4J?branch=master&svg=true)</a>
<a href="">[![Scc Count Badge](https://sloc.xyz/github/PanagiotisDrakatos/VDF4J/?category=lines)](https://github.com/PanagiotisDrakatos/VDF4J/)</a>
<a href="">![GitHub Repo stars](https://img.shields.io/github/stars/PanagiotisDrakatos/VDF4J?style=flat&logoColor=green)</a>
<a href="">![GitHub followers](https://img.shields.io/github/followers/PanagiotisDrakatos?style=flat&logo=green)</a>
<a href="">![GitHub forks](https://img.shields.io/github/forks/PanagiotisDrakatos/VDF4J?style=flat&logoColor=green)</a>
<a href="">![GitHub watchers](https://img.shields.io/github/watchers/PanagiotisDrakatos/VDF4J?style=flat&logoColor=green)</a>
<a href="">![GitHub contributors](https://badgen.net/github/contributors/PanagiotisDrakatos/VDF4J/)</a>
<a href="">![GitHub branches](https://badgen.net/github/branches/PanagiotisDrakatos/VDF4J/)</a>
<a href="">![GitHub language count](https://img.shields.io/github/languages/count/PanagiotisDrakatos/VDF4J?style=flat&logo=green)</a>
<a href="">![GitHub releases](https://badgen.net/github/releases/PanagiotisDrakatos/VDF4J/)</a>
<a href="">![GitHub Issues or Pull Requests](https://img.shields.io/github/issues/PanagiotisDrakatos/VDF4J?style=flat)</a>
<a href="">[![GitHub pull requests](https://img.shields.io/github/issues-pr/PanagiotisDrakatos/VDF4J.svg)](https://github.com/PanagiotisDrakatos/VDF4J/pulls)</a>
<a href="">![GitHub commit](https://badgen.net/github/commits/PanagiotisDrakatos/VDF4J)</a>
<a href="">![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/PanagiotisDrakatos/VDF4J/total?style=flat&logo=green)</a>
<a href="">![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/PanagiotisDrakatos/VDF4J?style=flat)</a>
<a href="">[![GitHub code-size](https://img.shields.io/github/languages/code-size/PanagiotisDrakato/VDF4J.svg)]()</a>
<a href="">![GitHub repo size](https://img.shields.io/github/repo-size/PanagiotisDrakatos/VDF4J?style=flat&logo=green)</a>
<a href="">[![License](https://img.shields.io/badge/license-Apache-green.svg)](https://github.com/PanagiotisDrakatos/VDF4J/blob/master/LICENSE)</a>

</div>

## Table of Contents

1. [Introduction](#introduction)
2. [What Is a Verifiable Delay Function (VDF)?](#what-is-a-verifiable-delay-function-vdf)
3. [Java Example Code](#java-example-code)
4. [Project Goals](#project-goals)
4. [Directory Structure](#directory-structure)
5. [Build & Run Instructions](#build--run-instructions)
6. [Building Native Library](#building-native-library)
6. [How It Works](#how-it-works)
    - [Core Concept](#core-concept)
    - [Computation Delay](#computation-delay)
    - [Proof Generation & Verification](#proof-generation--verification)
7. [Limitations & Future Work](#limitations--future-work)
8. .[Legal Warning](#legal-warning)
9. [Support](#support)
10. [Note](#note)
11. [Contribute](#contribute)
12. [Authors](#authors)
13. [License](#license)

---

## Introduction

**VDF4J** is a Java-based **Verifiable Delay Function** (VDF) implementation, developed primarily for **educational and research** purposes. VDFs require a predetermined amount of **sequential** time to compute yet are very fast to verify, making them attractive for use cases like **blockchain consensus**, **randomness beacons**, and **anti-front-running measures**.

---

## What Is a Verifiable Delay Function (VDF)?

This VDF implementation is written in Java. The GMP library is used for arithmetic and greatest common divisor (GCD) 
calculations. We use class groups to implement the 
approaches described in the following papers:

1. <a href="https://eprint.iacr.org/2018/627.pdf">Simple Verifiable Delay Functions</a> Pietrzak, 2018.
2. <a href="https://eprint.iacr.org/2018/623.pdf">Simple Efficient Verifiable Delay Functions</a> Wesolowski, 2018.

A Verifiable Delay Function (VDF) is a function that:

1. **Takes a certain minimum amount of time to compute** (no matter how many parallel processors you have, it cannot be computed faster than some specified minimum time).
2. **Generates a proof** that the output is correct.
3. **Allows very rapid verification** of that proof, much faster than the time taken to compute the original function.

In practice, VDFs use repeated or iterative 
cryptographic operations (often exponentiation in a
group with specific properties). The verification step 
uses cryptographic checks that confirm the original 
computation was done correctly. By introducing a time delay during evaluation, VDFs prevent malicious actors from influencing output. The output cannot be differentiated from a random number until the final result is computed. See https://eprint.iacr.org/2018/712.pdf for more details.

---
## Java Example Code 
```java
public class ExamplesTest {
    private static byte[] challenge;
    private static long difficulty;
    private static Random random;

    @BeforeAll
    public static void Setup() {
        challenge = new byte[20];
        difficulty = 100;
        random = new Random();
        random.setSeed(200);
    }

    @Test
    public void test_vdf_Pietrzak() throws NoSuchAlgorithmException {
        random.nextBytes(challenge);
        VdfEngine vdf = new VdfEnginePietrzak(2048);
        byte[] solution = vdf.solve(challenge, difficulty);
        assertEquals("0035e80b6a54563d78c34e365fd17f83fdb8946793b8d3c9fcda2bc257f57e806f72a8faee112a8b07c8cb4afa0d83a7f4e3bc87c4b2a63718a096f4bea1dc9fbb419c58131dc828ea9485ba24e8ef2ca5e68541545b859a106fc3d5da04ed6ede2614d7a7722334531cb4a8b9d6c2d50c68674c5e47b5eea2d89a720250fdf1d400134de5d604874b3c524a9ed4dfa4b892d8065d76f11d1d902561c56bc0960ed2f698caae3ab4c53e14b0cef2018313c6f4325944c44bebb56eb546871979b3242a938c9f3038e2ac2d9026b2c84867df2b2034aea6b2eec10c36b98975473a847cb004fb29a80e66777ed1e0feef4739b25a28defb6e1f7fc3e1e0985b2c6ecd", Hex.toHexString(solution));
        assertTrue(vdf.verify(challenge, difficulty, solution));
        vdf.cleanup();
    }
}
```
## Project Goals

1. **Illustrate VDF Principles**: Help developers and researchers understand the *mechanics* behind a verifiable delay function.
2. **Educational Resource**: Provide a clear, well-structured Java codebase as a learning tool.
3. **Modular Implementation**: Lay the groundwork so contributors can improve or adapt VDF logic for different use cases (blockchains, cryptographic protocols, etc.).

> **Important**: This implementation is intended for demonstration. It may not have production-grade security optimizations.

---

## Directory Structure

- **src/main/java/**  
  Contains the core Java source code implementing the VDF logic:
    - VDF function (sequential computation logic)
    - Proof generation/verification modules
    - Testing/demo classes

- **pom.xml**  
  The Maven configuration file that defines build dependencies and plugins.

- **appveyor.yml**  
  Potential integration/build instructions for *AppVeyor* continuous integration (not always used in Java projects, but can be adapted).

- **dependency-reduced-pom.xml**  
  An auxiliary Maven file generated during shading/assembly processes.

- **LICENSE**  
  MIT License details.

- **README.md**  
  You’re reading it!

---

## Build & Run Instructions

### Prerequisites
- **Java 8** or higher
- **Maven** (3.x or newer) or any other environment that can handle a Maven project

### Step-by-Step

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/PanagiotisDrakatos/VDF4J.git
   cd VDF4J
1. **Build via Maven:**
   This will compile the project and run any included tests.
   ```bash
   mvn clean install
3. **Run Demo Example (If Provided):**
  ```bash
   mvn exec:java -Dexec.mainClass="com.example.vdf.Main"
   ```
   Replace com.example.vdf.Main with the actual main class path from the repository. 
   This example shows how you might launch a test or demonstration of the VDF functionality.


## Building Native Library
Below are instructions for building the libgmp library 
that ships with this module

#### Linux

There is an included Dockerfile that can be used to compile libgmp with

First build the Docker image. Navigate to the <a href="https://github.com/PanagiotisDrakatos/VDF4J/tree/master/src/nativeCode" title="nativeCode lifestyles">nativeCode</a> folder of the project and run the following command:

    docker build -t jnagmp-linux-x86-64 -f linux-x86-64.dockerfile .

Next run the Docker image which will execute the [Makefile](Makefile.libgmp) and output the compiled library in to
_src/main/resources_

    docker run -v "$(pwd)/src/main/resources:/build/src/main/resources" -t jnagmp-linux-x86-64

#### Windows

## 1 Option: Using cygwin
### Prerequisites

- **Operating System**: Windows 7 or later (32-bit or 64-bit).
- **Internet Connection**: Required to download the installer and select packages.
- **Administrative Privileges**: Recommended for easier installation and future package management.

---

### Download Cygwin

1. **Navigate to the official [Cygwin website](https://cygwin.com/).**
2. **Choose the appropriate installer**:
    - `setup-x86_64.exe` for 64-bit Windows
    - `setup-x86.exe` for 32-bit Windows

> **Tip**: If you’re unsure of your system architecture, press `Windows Key + Pause/Break` to open system properties and check your system type.

---

### Run the Installer

1. **Double-click** the downloaded `setup-x86_64.exe` (or `setup-x86.exe` for 32-bit).
2. If prompted by **User Account Control (UAC)**, click **Yes** to allow changes.
3. **Choose “Install from Internet”** (typical usage) when asked “Choose a Download Source.”

---

### Select Packages

1. i686-w64-mingw32-gcc: For 32-bit Windows applications. Not exist on packet manger run version command to check it on
  cygwin
2. x86_64-w64-mingw32-gcc: For 64-bit Windows applications. Not exist on packet manger run version command to check it on
  cygwin

3. mingw64-i686-gcc-g++ - The GNU Compiler Collection (C++ compiler). For 32-bit Windows applications.
4. mingw64-x86_64-gcc-g++ - The GNU Compiler Collection (C++ compiler). For 64-bit Windows applications.

5. install cygwin on setup make sure all this installed
6. gcc-core - The GNU Compiler Collection (C compiler).
7. gcc-g++ - The GNU Compiler Collection (C compiler).
8. libgcc - The GNU Compiler Collection (C compiler).
9. make - The GNU version of the 'make' utility.
10. autoconf - A tool for automatically configuring source code.
11. automake - A tool for automatically generating Makefile.in files.
12. libtool - A generic library support script.
13. m4 - A macro processing language.
14. curl - A tool to transfer data from or to a server.

Add the following to your PATH environment variable

    C:\cygwin64\bin

Make sure you have the following run:

    i686-w64-mingw32-gcc --version For 32-bit Windows applications
    x86_64-w64-mingw32-gcc --version For 64-bit Windows applications

Navigate to the directory where you want to install run commands

    cd /cygdrive/c/Users/User/mypath...

Runs the following command to compile the library

     cd gmp-6.3.0
Run the following command to compile the library

    ./configure --disable-static --enable-shared --host=x86_64-w64-mingw32
OR This command

     make -f WindowsMakefile.libgmp install
Then run the following command to move the files into the resources folder that the program reads 
     
    mv libgmp-10.dll libgmp.dll

## How It Works

### Core Concept
1. **Input / Setup:**

   - A challenge input (typically an integer, group element, or random data) is prepared.
   - System parameters (e.g., group modulus for repeated squaring, number of iterations, etc.) 
     are established.
2. **Iterated Computation:**
     - The VDF function in this repository likely uses an iterative exponentiation or repeated squaring 
        approach— repeated many times to enforce a real-world time delay.
     - Due to the nature of exponentiation in large number fields, parallelization beyond a certain 
       point doesn’t help reduce the overall sequential time.
3. **Output + Proof:**
   - After the heavy sequential computation, the 
   function returns a result plus a cryptographic 
   proof of correctness (often involving repeated 
   squaring proofs or isogenies, depending on the chosen VDF approach).
   Computation Delay
### Computation Delay
   - The key to a VDF is that computing the output 
    from the input must take at least t steps
    (each step depends on the previous step’s result).

   - Even with many parallel processors, you cannot skip or batch these steps because each iteration relies on the output of the previous iteration.
   Proof Generation & Verification
### Computation Delay
   - **Proof**: Typically involves additional
     math that allows an observer to quickly confirm 
     that you actually performed all t steps.
   - **Verification**: By using cryptographic 
     checks (like checking an exponent or some tricky
     modular arithmetic property), the proof is 
     validated in log(t) or similarly small complexity.

## Limitations & Future Work

1. **Security Hardening:**
For real-world usage, consider larger parameters or proven secure group settings (e.g., class groups, pairing-friendly curves, incremental exponentiation in large prime fields, etc.).
2. **Performance Tuning:**
The Java code works for a demonstration; you may want advanced libraries or native bindings for enhanced big-integer arithmetic.
3. **Expand Proof Options:**
Different VDF constructions exist (e.g., Wesolowski’s proof, Pietrzak’s proof). This project could implement or compare multiple proof schemes.
4. **Benchmarking:**
Adding thorough benchmarks to measure performance under various iteration counts, machine architectures, or concurrency scenarios would be beneficial.

5.  **Integration with Other Projects:**
Useful for demonstrating real ownership or usage in blockchains or distributed systems needing
delayed computations

## Legal Warning
While this may be helpful for some, there are significant risks. VDF4J may be used only for
Educational Purposes. Do not use it as a ransomware! You could go to jail if if you will use it for
malicious purposes.<


## Support

For support, email panagiotisdrakatos@gmail.com or join me Discord:panos5427.
Meaning, if you liked using this app or it has helped you in any way,
I'd like you send me an email about anything you'd want to say about this software.
I'd really appreciate it!

## Note

- ⭐️ Give me a Star!! VDF4J is constantly updating, support us!
- The analysis was done by me, without having obfuscated the source code (either with pyarmor etc),
- I would not recommend using VDF4J + obfuscatebecause many times av trigger obfuscated codes as false positive
  even if legitimate.

## Contribute

1. Fork it: git clone https://github.com/PanagiotisDrakatos/VDF4J.git
2. Create your feature branch: git checkout -b my-new-feature
3. Commit your changes: git commit -am 'Add some feature'
4. Push to the branch: git push origin my-new-feature
5. Submit a pull request :D

## Authors

- [@panagiotisdrakatos](https://github.com/PanagiotisDrakatos)

## License

This project is distributed under the MIT license version 2.0 (see the LICENSE file in the project root).

By submitting a pull request to this project, you agree to license your contribution under the MIT license version 2.0
to this project.

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
=======
# Contribute
1. Fork it: git clone https://github.com/PanagiotisDrakatos/VDF4J.git
2. Create your feature branch: git checkout -b my-new-feature
3. Commit your changes: git commit -am 'Add some feature'
4. Push to the branch: git push origin my-new-feature
5. Submit a pull request :D

# License
This project is distributed under the MIT license version 2.0 (see the LICENSE file in the project root).

By submitting a pull request to this project, you agree to license your contribution under the MIT license version 2.0
to this project.

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
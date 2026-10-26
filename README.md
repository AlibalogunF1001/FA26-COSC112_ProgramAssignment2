# Monthly Utility Bill Calculator

**Program Assignment 2** · Due 10/22/2026, 11:59 PM

A Java program that calculates a customer's monthly electricity bill using tiered (progressive) pricing, applies a late-payment fee when the previous bill is overdue, then displays the bill and saves a copy to a text file.

## Deliverables

| Deliverable | Where |
| --- | --- |
| Algorithm design report (same format used in class) | Bb Ultra submission box |
| Source code | GitHub link shared on Bb Ultra |

## Features

- Collects customer information interactively (`Scanner` or `JOptionPane`) **or** reads a single record from an input file
- Calculates a progressive tiered electricity charge
- Adds a fixed $15.00 late fee when the previous bill is overdue (selection statement)
- Validates all input and reports errors without producing a normal bill
- Displays the bill with `JOptionPane` and writes the same content to `UtilityBillReport.txt`
- All monetary values are shown to two decimal places

> **Constraint:** loops are prohibited. On invalid input the program displays an error message and ends the current run; it does not re-prompt.

## Input

| Field | Description |
| --- | --- |
| Customer ID | Unique customer identifier |
| First name | Customer's first name |
| Last name | Customer's last name |
| Electricity usage | Kilowatt-hours (kWh) used |
| Payment status | `Y` = previous bill overdue, `N` = not overdue |

### Input file format

`UtilityData.txt` contains exactly one record. Fields are space-separated, and names contain no spaces:

```
CustomerID FirstName LastName Usage PaymentOverdue
```

Sample `UtilityData.txt`:

```
C1001 Maya Johnson 400 Y
```

## Pricing Rules

Rates are progressive: each rate applies only to the kWh used within its tier.

| Usage tier | Rate |
| --- | --- |
| First 100 kWh | $0.10 per kWh |
| Next 200 kWh (101–300) | $0.15 per kWh |
| Above 300 kWh | $0.20 per kWh |

**Late-payment fee:** $15.00 if the previous bill is overdue (`Y`), otherwise $0.00.

**Total amount due** = electricity charge + late-payment fee.

### Worked example (400 kWh, overdue)

| Portion | Calculation | Amount |
| --- | --- | --- |
| Tier 1 | 100 × $0.10 | $10.00 |
| Tier 2 | 200 × $0.15 | $30.00 |
| Tier 3 | 100 × $0.20 | $20.00 |
| **Electricity charge** | | **$60.00** |
| Late-payment fee | | $15.00 |
| **Total due** | | **$75.00** |

## Input Validation

The program rejects the record and displays an error message if:

- Customer ID or customer name is missing
- Electricity usage is negative
- Payment status is not a valid value (`Y` or `N`)
- The input file is missing (when reading from a file)

## Output

The bill is displayed in a `JOptionPane` dialog and saved to `UtilityBillReport.txt`. It includes the customer ID, name, usage, electricity charge, late-payment fee, and total amount due.

Expected output for the sample record:

```
MONTHLY UTILITY BILL
================================
Customer ID: C1001
Customer Name: Maya Johnson
Electricity Usage: 400 kWh
Electricity Charge: $60.00
Late-Payment Fee: $15.00
TOTAL AMOUNT DUE: $75.00
Payment Status: Previous bill overdue
```

Values must be calculated from the input, not hard-coded.

## Project Files

```
.
├── README.md
├── UtilityData.txt          # Sample input (one customer record)
├── UtilityBillReport.txt    # Generated bill (output)
└── <YourProgram>.java       # Main program
```

## Getting Started: Fork and Clone into IntelliJ IDEA

### Prerequisites

- A [GitHub](https://github.com) account
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Community or Ultimate)
- A JDK installed (Java 17 or newer recommended; IntelliJ can download one for you)
- Git installed (IntelliJ will prompt you to download it if it is missing)

### 1. Fork the repository

1. Sign in to GitHub and open the original repository page.
2. Click **Fork** in the top-right corner.
3. Choose your account as the owner, keep or edit the repository name, and click **Create fork**.
4. You now have your own copy at `https://github.com/<your-username>/<repository-name>`.

### 2. Clone your fork in IntelliJ

1. Open IntelliJ IDEA. On the Welcome screen click **Get from VCS**. If a project is already open, use **File > New > Project from Version Control**.
2. Select **Git** as the version control system.
3. Paste your fork's URL into the **URL** field:
   ```
   https://github.com/<your-username>/<repository-name>.git
   ```
   (Alternatively, choose **GitHub** in the left panel, sign in, and pick the repository from the list.)
4. Choose a local **Directory** for the project.
5. Click **Clone**, then click **Trust Project** when prompted.

### 3. Configure the project

1. Open **File > Project Structure > Project** and set the **SDK** to your installed JDK.
2. If your `.java` files are not recognized, right-click the folder containing them and choose **Mark Directory as > Sources Root**.
3. Make sure `UtilityData.txt` is located in the program's working directory. By default this is the project root. You can change it under **Run > Edit Configurations > Working directory**.

### 4. Run the program

1. Open the main `.java` file.
2. Click the green arrow in the gutter next to `main`, then choose **Run**.
3. Check the project root for the generated `UtilityBillReport.txt`.

### 5. Commit and push your work

1. Open **Git > Commit** (or press `Ctrl+K` on Windows/Linux, `Cmd+K` on macOS).
2. Select the changed files, write a commit message, and click **Commit**.
3. Open **Git > Push** (`Ctrl+Shift+K` / `Cmd+Shift+K`) and click **Push** to send the changes to your fork.
4. Copy your fork's GitHub URL and submit it on Bb Ultra.

### Optional: keep your fork in sync

If the original repository is updated, add it as an `upstream` remote and pull the changes:

```bash
git remote add upstream https://github.com/<original-owner>/<repository-name>.git
git fetch upstream
git merge upstream/main
```

In IntelliJ you can do the same under **Git > Manage Remotes** and **Git > Pull**.

## Build and Run

```bash
javac <YourProgram>.java
java <YourProgram>
```

Replace `<YourProgram>` with your main class name. Place `UtilityData.txt` in the working directory if reading from a file.

## Testing

Document the input, expected result, actual result, and pass/fail status for each case below.

| # | Test case | Expected behavior |
| --- | --- | --- |
| 1 | Usage of 80 kWh | Apply the first pricing tier |
| 2 | Usage of exactly 100 kWh | Apply the first tier correctly |
| 3 | Usage of exactly 300 kWh | Apply the first two tiers correctly |
| 4 | Usage of 400 kWh | Apply all three pricing tiers |
| 5 | Overdue status `Y` | Add $15.00 |
| 6 | Overdue status `N` | Add no late fee |
| 7 | Negative usage | Reject the record |
| 8 | Invalid payment status | Display an error message |
| 9 | Missing input file | Display an appropriate error message |

### Test log template

| # | Input | Expected result | Actual result | Pass/Fail |
| --- | --- | --- | --- | --- |
| 1 | | | | |

## Author

*Your name · Course · Semester*

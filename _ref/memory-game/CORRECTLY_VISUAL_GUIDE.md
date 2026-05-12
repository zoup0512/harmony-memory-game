# 🎮 Correctly - Visual Game Guide

## 🎯 Game Overview
**Correctly** (正确吗) is a problem-solving game where you must quickly judge whether math equations are correct or wrong.

---

## 📱 Game Screen

```
┌─────────────────────────────────┐
│ [←] 正确吗  [❤❤❤]  Score: 86   │
├─────────────────────────────────┤
│ Level 8                          │
├─────────────────────────────────┤
│                                 │
│           ┌────┐                │
│           │ 5  │ ← Question timer│
│           └────┘                │
│                                 │
│  ┌─────────────────────────┐   │
│  │                         │   │
│  │   5 + 3 - 2 = 6         │   │
│  │                         │   │
│  └─────────────────────────┘   │
│                                 │
│  ┌──────────┐  ┌──────────┐    │
│  │    ✓     │  │    ✗     │    │
│  │  正确    │  │  错误    │    │
│  └──────────┘  └──────────┘    │
│                                 │
│  Total Time: 45s                │
└─────────────────────────────────┘
```

---

## ⏱️ Timer System

### Question Timer (Top)
```
High Time (5-8s):  ┌────┐
                   │ 7  │ Green
                   └────┘

Medium Time (3-4s): ┌────┐
                    │ 3  │ Amber
                    └────┘

Low Time (1-2s):    ┌────┐
                    │ 1  │ Red (Pulsing!)
                    └────┘
```

### Total Game Timer
- **60 seconds** for entire game
- Displayed in top bar
- Game ends when reaches 0

---

## 📊 Equation Types by Level

### Level 1-3: Simple Addition/Subtraction
```
Easy examples:
4 + 2 = 6     ✓ Correct
7 - 3 = 5     ✗ Wrong (should be 4)
9 + 5 = 14    ✓ Correct
6 - 2 = 3     ✗ Wrong (should be 4)
```

### Level 4-5: Larger Numbers
```
Medium examples:
15 + 8 = 23   ✓ Correct
22 - 11 = 10  ✗ Wrong (should be 11)
18 + 12 = 30  ✓ Correct
25 - 14 = 12  ✗ Wrong (should be 11)
```

### Level 6-7: Multiplication
```
Multiplication:
3 × 4 = 12    ✓ Correct
5 × 6 = 31    ✗ Wrong (should be 30)
7 × 3 = 21    ✓ Correct
4 × 8 = 33    ✗ Wrong (should be 32)
```

### Level 8-9: Three Operations
```
More complex:
5 + 3 - 2 = 6     ✓ Correct
8 - 4 + 3 = 8     ✗ Wrong (should be 7)
10 + 5 - 3 = 12   ✓ Correct
7 - 2 + 4 = 10    ✗ Wrong (should be 9)
```

### Level 10-11: Larger Three Operations
```
Bigger numbers:
20 + 15 - 10 = 25   ✓ Correct
28 - 12 + 8 = 23    ✗ Wrong (should be 24)
30 + 18 - 15 = 33   ✓ Correct
```

### Level 12-15: Multiplication + Addition
```
Mixed operations:
5 × 4 + 10 = 30   ✓ Correct
6 × 3 - 8 = 11    ✗ Wrong (should be 10)
8 × 2 + 15 = 31   ✓ Correct
```

### Level 16-20: Two Multiplications
```
Very complex:
4 × 5 + 3 × 2 = 26   ✓ Correct
7 × 3 - 2 × 4 = 14   ✗ Wrong (should be 13)
5 × 6 + 4 × 3 = 42   ✓ Correct
```

### Level 21+: Expert Level
```
Extremely complex:
12 × 15 + 8 × 10 - 50 = 210   ✓ Correct
20 × 18 - 15 × 12 + 100 = 279 ✗ Wrong (should be 280)
25 × 12 + 18 × 15 - 200 = 370 ✓ Correct
```

---

## 🎮 Gameplay Flow

### 1. Question Appears
```
┌─────────────────────────────┐
│        ┌────┐               │
│        │ 4  │ Timer starts  │
│        └────┘               │
│                             │
│  ┌─────────────────────┐   │
│  │   5 + 3 = 8         │   │
│  └─────────────────────┘   │
│                             │
│  [正确]      [错误]         │
└─────────────────────────────┘
```

### 2. Player Thinks
```
Is 5 + 3 = 8?
Let me calculate...
5 + 3 = 8 ✓
Yes, it's correct!
```

### 3. Player Clicks
```
Click [正确] button
```

### 4. Feedback
```
Correct Answer:
┌─────────────────────────────┐
│  ┌─────────────────────┐   │
│  │   5 + 3 = 8    ✓    │   │ Green!
│  └─────────────────────┘   │
│                             │
│  +6 points                  │
│  Next question...           │
└─────────────────────────────┘

Wrong Answer:
┌─────────────────────────────┐
│  ┌─────────────────────┐   │
│  │   5 + 3 = 9    ✗    │   │ Red!
│  └─────────────────────┘   │
│                             │
│  -1 life                    │
│  Try again...               │
└─────────────────────────────┘
```

---

## 💡 Strategy Tips

### Tip 1: Mental Math Practice
```
Level 1-5: Basic arithmetic
- Practice addition/subtraction
- Should be instant

Level 6-10: Multiplication
- Know your times tables
- 2-8 times tables most common

Level 11+: Order of operations
- Multiplication first!
- Then addition/subtraction
- Left to right
```

### Tip 2: Quick Estimation
```
For complex equations:
20 × 15 + 10 × 8 - 50 = ?

Quick estimate:
20 × 15 ≈ 300
10 × 8 = 80
300 + 80 - 50 = 330

If answer is 330 → Correct
If answer is 250 → Wrong
```

### Tip 3: Common Errors
```
Wrong answers are usually off by:
- Level 1-5: ±1 to ±3
- Level 6-10: ±1 to ±5
- Level 11+: ±10 to ±40

If answer seems way off → Probably wrong
If answer is close → Need to calculate
```

### Tip 4: Time Management
```
Easy questions (Level 1-5):
- Should take 1-2 seconds
- Don't overthink

Hard questions (Level 10+):
- Use full 8 seconds if needed
- Better to be accurate than fast
```

### Tip 5: Stay Calm
```
When timer is red and pulsing:
- Don't panic
- Make your best guess
- Wrong answer = -1 life
- Timeout = -1 life (same penalty)
```

---

## 📈 Scoring Examples

### Quick Game (Level 5)
```
Level 1: 4 + 2 = 6 ✓     +6 points
Level 2: 7 - 3 = 5 ✗     -1 life
Level 2: 8 + 1 = 9 ✓     +8 points
Level 3: 5 + 4 = 9 ✓     +10 points
Level 4: 12 - 5 = 7 ✓    +12 points
Level 5: 18 + 7 = 25 ✓   +14 points
─────────────────────────────
Total: 50 points

Lives: ❤❤
Time: 45s remaining
Stars: ⭐
```

### Good Game (Level 12)
```
Levels 1-12 completed
Few mistakes
Good mental math

Total: ~156 points
Lives: ❤
Time: 25s remaining
Stars: ⭐⭐
```

### Excellent Game (Level 20)
```
Levels 1-20 completed
Very few mistakes
Expert mental math

Total: ~420 points
Lives: ❤❤
Time: 10s remaining
Stars: ⭐⭐⭐
```

---

## 🎯 Visual States

### Playing State
```
┌─────────────────────────────┐
│        ┌────┐               │
│        │ 5  │ Green timer   │
│        └────┘               │
│                             │
│  ┌─────────────────────┐   │
│  │   5 + 3 = 8         │   │ Purple
│  └─────────────────────┘   │
│                             │
│  [正确]      [错误]         │ Enabled
└─────────────────────────────┘
```

### Correct Answer
```
┌─────────────────────────────┐
│  ┌─────────────────────┐   │
│  │   5 + 3 = 8    ✓    │   │ Green!
│  └─────────────────────┘   │ Scales up
│                             │
│  Buttons disabled           │
│  Next question in 0.5s      │
└─────────────────────────────┘
```

### Wrong Answer
```
┌─────────────────────────────┐
│  ┌─────────────────────┐   │
│  │   5 + 3 = 9    ✗    │   │ Red!
│  └─────────────────────┘   │ Scales down
│                             │
│  -1 life                    │
│  Same question again        │
└─────────────────────────────┘
```

### Low Time Warning
```
┌─────────────────────────────┐
│        ┌────┐               │
│        │ 1  │ Red + Pulsing!│
│        └────┘               │
│                             │
│  Hurry up!                  │
└─────────────────────────────┘
```

---

## 🏆 Achievement Levels

### Beginner (Level 1-5)
```
⭐ 0 Stars
"Learning the basics"
Simple arithmetic
```

### Intermediate (Level 6-11)
```
⭐ 1 Star
"Getting better!"
Multiplication and more
```

### Advanced (Level 12-19)
```
⭐⭐ 2 Stars
"Math whiz!"
Complex operations
```

### Expert (Level 20+)
```
⭐⭐⭐ 3 Stars
"Mental math master!"
Expert level equations
```

---

## 🎨 Color Coding

### Timer Colors
```
🟢 Green (5-8s): Plenty of time
🟡 Amber (3-4s): Time running low
🔴 Red (1-2s):   Hurry up!
```

### Equation Colors
```
🟣 Purple: Normal state
🟢 Green:  Correct answer
🔴 Red:    Wrong answer
```

### Button Colors
```
🟢 Green button:  正确 (Correct)
🔴 Red button:    错误 (Wrong)
```

---

## 💭 Example Decisions

### Easy Decision
```
Equation: 2 + 2 = 4
Thought: "Obviously correct"
Answer: 正确 ✓
Result: Correct! +6 points
```

### Medium Decision
```
Equation: 7 × 8 = 56
Thought: "7 × 8... yes, 56"
Answer: 正确 ✓
Result: Correct! +18 points
```

### Hard Decision
```
Equation: 15 × 12 + 8 × 9 - 50 = 202
Thought: "15×12=180, 8×9=72, 180+72=252, 252-50=202"
Answer: 正确 ✓
Result: Correct! +38 points
```

### Tricky Decision
```
Equation: 6 × 7 + 3 = 45
Thought: "6×7=42, 42+3=45, looks right"
Answer: 正确 ✓
Result: Correct! +26 points

Equation: 6 × 7 + 3 = 46
Thought: "Wait, 6×7=42, 42+3=45, not 46!"
Answer: 错误 ✓
Result: Correct! +26 points
```

---

## 🎮 Controls

### Touch Interactions
- **Tap 正确**: Answer "Correct"
- **Tap 错误**: Answer "Wrong"
- **Tap Back**: Exit game

### Visual Feedback
- **Timer Color**: Shows urgency
- **Timer Pulse**: Critical time
- **Equation Scale**: Answer feedback
- **Icon Display**: Success/failure

---

**Game**: Correctly (正确吗)  
**Category**: Problem Solving (问题解决)  
**Difficulty**: Easy to Expert  
**Best For**: Mental math and quick thinking training  
**Time Limit**: 60 seconds total, 4-8s per question  
**Lives**: 3

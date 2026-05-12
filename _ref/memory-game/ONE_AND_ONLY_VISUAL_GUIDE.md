# 🎮 One and Only - Visual Game Guide

## 🎯 Game Overview
**One and Only** (唯一的) is a speed-based game where you must quickly find the element that is unique by color OR shape.

---

## 📱 Game Screen

```
┌─────────────────────────────────┐
│ [←] 唯一的          Score: 120  │
├─────────────────────────────────┤
│ Level 8                          │
├─────────────────────────────────┤
│ ████████████████░░░░░░░░ 45s    │ ← Timer
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │  找出唯一不同的元素！        │ │
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│                                 │
│  ┌──┐ ┌──┐ ┌──┐ ┌──┐           │
│  │●│ │●│ │■│ │■│              │
│  └──┘ └──┘ └──┘ └──┘           │
│  ┌──┐ ┌──┐ ┌──┐ ┌──┐           │
│  │▲│ │▲│ │◆│ │◆│              │
│  └──┘ └──┘ └──┘ └──┘           │
│  ┌──┐                           │
│  │★│ ← This one is unique!      │
│  └──┘                           │
│                                 │
└─────────────────────────────────┘
```

---

## 🎨 Shapes

### Circle (●)
```
  ●●●●
 ●    ●
●      ●
●      ●
 ●    ●
  ●●●●
```

### Square (■)
```
■■■■■■
■    ■
■    ■
■    ■
■    ■
■■■■■■
```

### Triangle (▲)
```
    ▲
   ▲ ▲
  ▲   ▲
 ▲     ▲
▲▲▲▲▲▲▲
```

### Diamond (◆)
```
    ◆
   ◆ ◆
  ◆   ◆
   ◆ ◆
    ◆
```

---

## 🌈 Colors

### Available Colors
- 🔴 **Red** (#E53935)
- 🔵 **Blue** (#1E88E5)
- 🟢 **Green** (#43A047)
- 🟡 **Yellow** (#FDD835)
- 🟣 **Purple** (#8E24AA)
- 🟠 **Orange** (#FB8C00)

---

## 📊 Difficulty Levels

### Level 1-2: Beginner
```
Elements: 3-4
Groups: 2
Colors: 2

Example:
┌──┐ ┌──┐ ┌──┐
│●│ │●│ │■│  ← Unique by shape
└──┘ └──┘ └──┘
Red  Red  Blue
```

### Level 3-5: Easy
```
Elements: 5-8
Groups: 3
Colors: 3

Example:
┌──┐ ┌──┐ ┌──┐ ┌──┐ ┌──┐
│●│ │●│ │■│ │■│ │▲│  ← Unique
└──┘ └──┘ └──┘ └──┘ └──┘
Red  Red  Blue Blue Green
```

### Level 6-10: Medium
```
Elements: 9-14
Groups: 4
Colors: 3

Example:
┌──┐ ┌──┐ ┌──┐ ┌──┐
│●│ │●│ │■│ │■│
└──┘ └──┘ └──┘ └──┘
┌──┐ ┌──┐ ┌──┐ ┌──┐
│▲│ │▲│ │◆│ │◆│
└──┘ └──┘ └──┘ └──┘
┌──┐
│★│  ← Unique element
└──┘
```

### Level 11-20: Hard
```
Elements: 15-25
Groups: 5
Colors: 3

Example: 15 elements in 3x5 grid
┌──┐ ┌──┐ ┌──┐ ┌──┐ ┌──┐
│●│ │●│ │■│ │■│ │▲│
└──┘ └──┘ └──┘ └──┘ └──┘
┌──┐ ┌──┐ ┌──┐ ┌──┐ ┌──┐
│▲│ │◆│ │◆│ │●│ │●│
└──┘ └──┘ └──┘ └──┘ └──┘
┌──┐ ┌──┐ ┌──┐ ┌──┐ ┌──┐
│■│ │■│ │★│ │▲│ │▲│
└──┘ └──┘ └──┘ └──┘ └──┘
```

### Level 21+: Expert
```
Elements: 26-50
Groups: 5
Colors: 3

Example: 30 elements in 5x6 grid
Many elements, harder to spot unique one!
```

---

## 🎯 How to Find the Unique Element

### Strategy 1: Count by Color
```
Count each color:
Red: 6 elements
Blue: 6 elements
Green: 1 element  ← Only one!

Answer: The green element
```

### Strategy 2: Count by Shape
```
Count each shape:
Circles: 8 elements
Squares: 8 elements
Triangles: 1 element  ← Only one!

Answer: The triangle
```

### Strategy 3: Look for Odd One Out
```
Most elements: Red circles
Some elements: Blue squares
One element: Green triangle  ← Different!

Answer: The green triangle
```

---

## 💡 Example Scenarios

### Scenario 1: Unique by Color
```
┌──┐ ┌──┐ ┌──┐ ┌──┐ ┌──┐
│●│ │●│ │●│ │●│ │●│
└──┘ └──┘ └──┘ └──┘ └──┘
Red  Red  Red  Red  Blue ← UNIQUE!

All circles, but one is blue
Answer: Blue circle
```

### Scenario 2: Unique by Shape
```
┌──┐ ┌──┐ ┌──┐ ┌──┐ ┌──┐
│●│ │●│ │●│ │●│ │■│
└──┘ └──┘ └──┘ └──┘ └──┘
Red  Red  Red  Red  Red ← UNIQUE!

All red, but one is square
Answer: Red square
```

### Scenario 3: Unique by Both
```
┌──┐ ┌──┐ ┌──┐ ┌──┐ ┌──┐
│●│ │●│ │■│ │■│ │▲│
└──┘ └──┘ └──┘ └──┘ └──┘
Red  Red  Blue Blue Green ← UNIQUE!

Different color AND shape
Answer: Green triangle
```

---

## ⏱️ Timer System

### 60-Second Countdown
```
Start:  ████████████████████ 60s
        ↓
Mid:    ██████████░░░░░░░░░░ 30s
        ↓
Low:    ███░░░░░░░░░░░░░░░░░ 10s (Hurry!)
        ↓
End:    ░░░░░░░░░░░░░░░░░░░░  0s (Game Over)
```

### No Lives System
- Unlike other games, no lives
- Only timer matters
- Keep playing until time runs out
- Try to reach highest level possible

---

## 📈 Scoring Examples

### Quick Game (Level 5)
```
Level 1: 10 points  (8 + 1*2)
Level 2: 12 points  (8 + 2*2)
Level 3: 14 points  (8 + 3*2)
Level 4: 16 points  (8 + 4*2)
Level 5: 18 points  (8 + 5*2)
─────────────────────────────
Total:   70 points

Time: 45s remaining
Stars: ⭐ (reached level 5)
```

### Good Game (Level 15)
```
Levels 1-15 completed
Total: ~240 points

Time: 20s remaining
Stars: ⭐⭐ (reached level 15)
```

### Excellent Game (Level 30)
```
Levels 1-30 completed
Total: ~930 points

Time: 5s remaining
Stars: ⭐⭐⭐ (reached level 30)
```

---

## 🎮 Gameplay Flow

### 1. Level Starts
```
┌─────────────────────────────┐
│  找出唯一不同的元素！        │
└─────────────────────────────┘

Elements appear
All elements pulse
Timer starts counting
```

### 2. Player Searches
```
Look at all elements
Compare colors
Compare shapes
Find the unique one
```

### 3. Player Clicks
```
Correct:
┌──┐
│✓│  ← Scales up, green border
└──┘
+10 points
Next level in 0.5s

Wrong:
┌──┐
│✗│  ← Brief flash
└──┘
No penalty
Continue playing
```

### 4. Next Level
```
More elements appear
Difficulty increases
Timer continues
Keep playing!
```

### 5. Time Runs Out
```
┌─────────────────────────────┐
│      时间到！ (Time's Up!)   │
│      Final Score: 240       │
│      Level Reached: 15      │
│      Stars: ⭐⭐            │
└─────────────────────────────┘
```

---

## 💡 Pro Tips

### Tip 1: Scan Quickly
```
Don't focus on one element
Scan the whole grid
Look for patterns
Spot the odd one out
```

### Tip 2: Use Peripheral Vision
```
Don't stare at center
Use your peripheral vision
Differences pop out
Faster recognition
```

### Tip 3: Color First
```
Colors are easier to spot
Check color distribution first
Then check shapes
Faster strategy
```

### Tip 4: Practice Pattern Recognition
```
After a few games:
- You'll recognize patterns faster
- Common combinations become obvious
- Unique elements stand out more
```

### Tip 5: Stay Calm
```
Don't panic as timer runs low
Mistakes don't cost lives
Take a breath
Focus on accuracy
```

---

## 🎯 Common Patterns

### Pattern 1: Color Majority
```
Many red, few blue, one green
Answer: Green (unique color)
```

### Pattern 2: Shape Majority
```
Many circles, few squares, one triangle
Answer: Triangle (unique shape)
```

### Pattern 3: Color-Shape Combo
```
Red circles, blue squares, one yellow triangle
Answer: Yellow triangle (unique both)
```

### Pattern 4: Subtle Difference
```
All similar, but one slightly different
Look carefully!
```

---

## 🏆 Achievement Levels

### Beginner (Level 1-9)
```
⭐ 0 Stars
"Just getting started"
Focus on learning patterns
```

### Intermediate (Level 10-19)
```
⭐ 1 Star
"Getting the hang of it"
Patterns becoming familiar
```

### Advanced (Level 20-29)
```
⭐⭐ 2 Stars
"Speed demon!"
Quick pattern recognition
```

### Expert (Level 30+)
```
⭐⭐⭐ 3 Stars
"Master of uniqueness!"
Lightning-fast recognition
```

---

## 🎨 Visual States

### Playing State
```
All elements pulse gently
White background
Gray borders
Ready to click
```

### Correct Answer
```
Winning element:
- Scales to 1.2x
- Green border appears
- Brief celebration
- Next level loads
```

### Wrong Answer
```
Brief error flash
No visual change to element
Continue playing
No penalty
```

### Time Running Out
```
Progress bar turns red
Last 10 seconds
Hurry up!
```

---

**Game**: One and Only (唯一的)  
**Category**: Speed (速度)  
**Difficulty**: Easy to Expert  
**Best For**: Quick thinking and pattern recognition training  
**Time Limit**: 60 seconds  
**Max Level**: 46

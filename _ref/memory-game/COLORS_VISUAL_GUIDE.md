# 🎨 Colors Game Visual Guide

## 🎮 Game Overview
**Colors** is a Stroop effect game that tests cognitive flexibility by showing color words in different colors.

---

## 📱 Screen Layout

### Full Screen View
```
┌─────────────────────────────────────────┐
│  [⏸]        颜色                        │  <- Header (no lives)
├─────────────────────────────────────────┤
│              关卡 5                      │  <- Level Display
├─────────────────────────────────────────┤
│  ████████████████░░░░░░░░░░  15s       │  <- Timer Progress Bar
├─────────────────────────────────────────┤
│                                         │
│                                         │
│         ┌─────────────────┐             │
│         │                 │             │
│         │                 │             │
│         │      RED        │             │  <- Color Word
│         │   (in blue)     │             │     (56sp, bold)
│         │                 │             │
│         │                 │             │
│         └─────────────────┘             │
│                                         │
│                                         │
│                                         │
│      ┌──────────┐  ┌──────────┐        │
│      │          │  │          │        │
│      │   匹配   │  │  不匹配  │        │  <- Answer Buttons
│      │  Match   │  │Don't Match│        │     (72dp height)
│      │          │  │          │        │
│      └──────────┘  └──────────┘        │
│                                         │
│                                         │
└─────────────────────────────────────────┘
```

---

## 🎨 Color Examples

### Example 1: Match Scenario
```
┌─────────────────┐
│                 │
│      RED        │  <- Word: "RED"
│   (in red)      │  <- Color: Red (#F44336)
│                 │  -> Answer: MATCH ✓
└─────────────────┘
```

### Example 2: Don't Match Scenario
```
┌─────────────────┐
│                 │
│      BLUE       │  <- Word: "BLUE"
│   (in yellow)   │  <- Color: Yellow (#FFEB3B)
│                 │  -> Answer: DON'T MATCH ✓
└─────────────────┘
```

### Example 3: Another Match
```
┌─────────────────┐
│                 │
│     GREEN       │  <- Word: "GREEN"
│   (in green)    │  <- Color: Green (#4CAF50)
│                 │  -> Answer: MATCH ✓
└─────────────────┘
```

### Example 4: Another Don't Match
```
┌─────────────────┐
│                 │
│     PURPLE      │  <- Word: "PURPLE"
│   (in orange)   │  <- Color: Orange (#FF9800)
│                 │  -> Answer: DON'T MATCH ✓
└─────────────────┘
```

---

## 🎨 Available Colors

### Color Palette
```
1. RED     (#F44336)  ████  <- Red
2. BLUE    (#2196F3)  ████  <- Blue
3. GREEN   (#4CAF50)  ████  <- Green
4. YELLOW  (#FFEB3B)  ████  <- Yellow
5. PURPLE  (#9C27B0)  ████  <- Purple
6. ORANGE  (#FF9800)  ████  <- Orange
```

### Color Combinations
- **Total Combinations**: 6 words × 6 colors = 36 combinations
- **Match Combinations**: 6 (word matches color)
- **Don't Match Combinations**: 30 (word doesn't match color)
- **Probability of Match**: 1/6 ≈ 16.7%

---

## 🎬 Animation States

### 1. Normal State
```
┌─────────────────┐
│                 │
│      RED        │  <- Static display
│   (in blue)     │
│                 │
└─────────────────┘

[  匹配  ] [不匹配]  <- Buttons enabled (colored)
```

### 2. Correct Answer - Hint Appears
```
┌─────────────────────────────┐
│                             │
│    ┌─────────────────┐      │
│    │                 │      │
│    │     正确！      │      │  <- "CORRECT!" hint
│    │   (CORRECT!)    │      │     (48sp, white on green)
│    │                 │      │     Scale: 0 → 1 (spring)
│    └─────────────────┘      │     Alpha: 0 → 1 → 0
│                             │
└─────────────────────────────┘
```

### 3. Hint Fades Out (500ms)
```
┌─────────────────────────────┐
│                             │
│    ┌─────────────────┐      │
│    │                 │      │
│    │     正确！      │      │  <- Fading out
│    │   (fading...)   │      │     Alpha: 1 → 0
│    │                 │      │     Scale: 1 → 1
│    └─────────────────┘      │
│                             │
└─────────────────────────────┘
```

### 4. New Question Appears
```
┌─────────────────┐
│                 │
│     GREEN       │  <- New word/color
│   (in purple)   │     Immediately after hint
│                 │
└─────────────────┘
```

### 5. Game Over State
```
┌─────────────────┐
│                 │
│     YELLOW      │  <- Last question
│   (in red)      │
│                 │
└─────────────────┘

[  匹配  ] [不匹配]  <- Buttons disabled (gray)
```

---

## 🎯 Button States

### Match Button (Left)
```
┌──────────────┐
│              │
│     匹配     │  <- Green (#4CAF50)
│    Match     │     72dp height
│              │     Bold, 24sp
└──────────────┘
```

### Don't Match Button (Right)
```
┌──────────────┐
│              │
│   不匹配     │  <- Red (#F44336)
│ Don't Match  │     72dp height
│              │     Bold, 24sp
└──────────────┘
```

### Disabled State (Game Over)
```
┌──────────────┐
│              │
│     匹配     │  <- Gray (#BDBDBD)
│    Match     │     Not clickable
│              │
└──────────────┘
```

---

## ⏱️ Timer Display

### Progress Bar States

#### Full Time (30s)
```
████████████████████████████████  30s
```

#### Half Time (15s)
```
████████████████░░░░░░░░░░░░░░░░  15s
```

#### Low Time (5s)
```
█████░░░░░░░░░░░░░░░░░░░░░░░░░░░   5s
```

#### Time Out (0s)
```
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░   0s  -> Game Over
```

---

## 🎮 Gameplay Flow Visualization

### Complete Game Sequence
```
START
  ↓
┌─────────────────────────────────┐
│ Level 1 | Timer: 30s | Score: 0 │
│                                 │
│         RED (in red)            │  <- Match
│                                 │
│    [匹配] [不匹配]              │
└─────────────────────────────────┘
  ↓ Click "匹配"
  ↓
┌─────────────────────────────────┐
│ Level 1 | Timer: 28s | Score: 0 │
│                                 │
│        正确！                   │  <- Hint appears
│                                 │
└─────────────────────────────────┘
  ↓ 500ms later
  ↓
┌─────────────────────────────────┐
│ Level 2 | Timer: 27s | Score: 8 │
│                                 │
│      BLUE (in yellow)           │  <- Don't Match
│                                 │
│    [匹配] [不匹配]              │
└─────────────────────────────────┘
  ↓ Click "不匹配"
  ↓
┌─────────────────────────────────┐
│ Level 2 | Timer: 26s | Score: 8 │
│                                 │
│        正确！                   │  <- Hint appears
│                                 │
└─────────────────────────────────┘
  ↓ 500ms later
  ↓
┌─────────────────────────────────┐
│ Level 3 | Timer: 25s | Score: 16│
│                                 │
│     GREEN (in blue)             │  <- Don't Match
│                                 │
│    [匹配] [不匹配]              │
└─────────────────────────────────┘
  ↓ Click "匹配" (WRONG!)
  ↓
GAME OVER
```

---

## 🎨 Color Word Display Details

### Typography
```
Font Size:    56sp
Font Weight:  Bold
Alignment:    Center
Color:        Dynamic (one of 6 colors)
Background:   White
Elevation:    8dp
Corner Radius: 16dp
Padding:      64dp vertical, 32dp horizontal
```

### Example Rendering
```
┌─────────────────────────────────┐
│                                 │  <- 64dp padding top
│                                 │
│            PURPLE               │  <- 56sp bold
│         (in orange)             │     Orange color
│                                 │
│                                 │  <- 64dp padding bottom
└─────────────────────────────────┘
     ↑                         ↑
  32dp padding            32dp padding
```

---

## 🎯 Stroop Effect Visualization

### Congruent (Easy - Match)
```
Word:  RED
Color: ████ (Red)
Brain: "RED" + Red = MATCH ✓
Speed: Fast (automatic)
```

### Incongruent (Hard - Don't Match)
```
Word:  RED
Color: ████ (Blue)
Brain: "RED" ≠ Blue = DON'T MATCH ✓
Speed: Slower (requires inhibition)
Conflict: Word reading vs color naming
```

### Cognitive Process
```
See Word "RED" in Blue
        ↓
    ┌───────┴───────┐
    ↓               ↓
Read "RED"    See Blue Color
(automatic)   (requires focus)
    ↓               ↓
    └───────┬───────┘
            ↓
    Compare & Decide
            ↓
    Don't Match!
```

---

## 📊 Score Display

### Score Progression
```
Level 1:  0 points  → Answer correct → 8 points
Level 2:  8 points  → Answer correct → 16 points
Level 3: 16 points  → Answer correct → 24 points
Level 4: 24 points  → Answer correct → 32 points
Level 5: 32 points  → Answer correct → 40 points
...
```

### Stars Calculation
```
Score:  0-19  → ☆☆☆ (0 stars)
Score: 20-39  → ★☆☆ (1 star)
Score: 40-79  → ★★☆ (2 stars)
Score: 80+    → ★★★ (3 stars)
```

---

## 🎬 Animation Timeline

### Correct Answer Sequence
```
Time: 0ms
┌─────────────────┐
│     BLUE        │  <- Question displayed
│   (in blue)     │
└─────────────────┘
[匹配] [不匹配]

Time: 0ms (click "匹配")
↓

Time: 0-500ms
┌─────────────────┐
│   正确！        │  <- Hint animating
│  (scaling up)   │     Scale: 0 → 1 (spring)
└─────────────────┘     Alpha: 0 → 1

Time: 500ms
┌─────────────────┐
│   正确！        │  <- Hint fading
│  (fading out)   │     Alpha: 1 → 0
└─────────────────┘

Time: 500ms
┌─────────────────┐
│    GREEN        │  <- New question
│  (in yellow)    │     Appears immediately
└─────────────────┘
[匹配] [不匹配]
```

---

## 🎨 Color Contrast Examples

### High Contrast (Easy to See)
```
1. RED on White    ████ (Very visible)
2. BLUE on White   ████ (Very visible)
3. GREEN on White  ████ (Very visible)
4. PURPLE on White ████ (Very visible)
```

### Medium Contrast
```
5. YELLOW on White ████ (Visible but lighter)
6. ORANGE on White ████ (Visible)
```

---

## 🎮 User Experience Flow

### First Time Player
```
1. See "RED" in red
   → Think: "They match!"
   → Click: "匹配"
   → Result: ✓ Correct!

2. See "BLUE" in red
   → Think: "They don't match!"
   → Click: "不匹配"
   → Result: ✓ Correct!

3. See "GREEN" in green
   → Think: "They match!"
   → Click: "匹配"
   → Result: ✓ Correct!

4. See "YELLOW" in blue
   → Think: "Wait... yellow... blue..."
   → Click: "不匹配"
   → Result: ✓ Correct!
```

### Experienced Player (Fast)
```
1. RED/red    → Match    → ✓ (instant)
2. BLUE/yellow → No Match → ✓ (instant)
3. GREEN/green → Match    → ✓ (instant)
4. PURPLE/orange → No Match → ✓ (instant)
5. ORANGE/orange → Match   → ✓ (instant)
```

---

## 📱 Responsive Design

### Portrait Mode (Standard)
```
┌─────────────────┐
│     Header      │
│     Level       │
│   Progress Bar  │
│                 │
│   Color Word    │  <- Large, centered
│                 │
│   [匹配][不匹配]│  <- Side by side
│                 │
└─────────────────┘
```

### Landscape Mode (Adapted)
```
┌─────────────────────────────────┐
│ Header | Level | Progress       │
│                                 │
│  Color Word    [匹配] [不匹配] │  <- Horizontal layout
│                                 │
└─────────────────────────────────┘
```

---

## 🎯 Key Visual Elements

### 1. Color Word Card
- **Background**: White
- **Elevation**: 8dp shadow
- **Corner Radius**: 16dp
- **Text Size**: 56sp
- **Text Weight**: Bold
- **Padding**: 64dp vertical

### 2. Answer Buttons
- **Height**: 72dp
- **Corner Radius**: 12dp
- **Text Size**: 24sp
- **Text Weight**: Bold
- **Colors**: Green (Match), Red (Don't Match)

### 3. Correct Hint
- **Background**: Green (#4CAF50)
- **Corner Radius**: 24dp
- **Text Size**: 48sp
- **Text Color**: White
- **Elevation**: 16dp shadow
- **Animation**: Spring scale + fade

### 4. Progress Bar
- **Height**: 4dp
- **Color**: Blue (#2196F3)
- **Track Color**: Gray (#E0E0E0)
- **Updates**: Every 50ms

---

## 🎉 Success Feedback

### Visual Feedback
```
Correct Answer:
  ↓
┌─────────────────┐
│                 │
│    正确！       │  <- Green background
│   CORRECT!      │     White text
│                 │     Large (48sp)
└─────────────────┘     Bouncy animation
```

### Timing
- **Appear**: 0ms (instant)
- **Scale**: 0 → 1 (spring, ~300ms)
- **Hold**: Full opacity for ~200ms
- **Fade**: Alpha 1 → 0 (500ms total)
- **Next**: New question appears

---

**Visual Guide Complete!**  
Ready to play Colors game! 🎨


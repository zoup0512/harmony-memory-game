# 🎮 Rotating Grid - Visual Game Flow Guide

## 🎯 Game Overview
**Rotating Grid** is an attention-based memory game where players must track grid cell positions through rotation.

---

## 📱 Game Flow Visualization

### Phase 1: Ready (800ms)
```
┌─────────────────────────────────┐
│ [⏸] 旋转网格 [❤❤❤]              │
├─────────────────────────────────┤
│ Level 1                          │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │      准备好！ (Ready!)       │ │
│ │      [Green indicator]      │ │
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│                                 │
│      [Empty grid space]         │
│                                 │
└─────────────────────────────────┘
```

### Phase 2: Grid Animation (1000ms)
```
┌─────────────────────────────────┐
│ [⏸] 旋转网格 [❤❤❤]              │
├─────────────────────────────────┤
│ Level 1                          │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │      注意... (Attention)     │ │
│ │      [Blue indicator]       │ │
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│                                 │
│      ┌───┬───┬───┐              │
│      │   │   │   │  (Grid       │
│      ├───┼───┼───┤   scales     │
│      │   │   │   │   in with    │
│      ├───┼───┼───┤   bounce)    │
│      │   │   │   │              │
│      └───┴───┴───┘              │
│                                 │
└─────────────────────────────────┘
```

### Phase 3: Show Challenge (1200ms + level*200ms)
```
┌─────────────────────────────────┐
│ [⏸] 旋转网格 [❤❤❤]              │
├─────────────────────────────────┤
│ Level 1                          │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │   记住这些位置！              │ │
│ │   [Amber indicator]          │ │
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│                                 │
│      ┌───┬───┬───┐              │
│      │ ● │   │   │  ● = Pattern │
│      ├───┼───┼───┤  (Teal color,│
│      │   │ ● │   │   pulsing)   │
│      ├───┼───┼───┤              │
│      │   │   │ ● │              │
│      └───┴───┴───┘              │
│                                 │
│   MEMORIZE THESE POSITIONS!     │
└─────────────────────────────────┘
```

### Phase 4: Rotation (2000ms)
```
┌─────────────────────────────────┐
│ [⏸] 旋转网格 [❤❤❤]              │
├─────────────────────────────────┤
│ Level 1                          │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │ 🔄 网格旋转中... 90°         │ │
│ │    [Teal indicator]          │ │
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│                                 │
│         ┌───┬───┬───┐           │
│         │   │   │   │           │
│         ├───┼───┼───┤  (Grid    │
│         │   │   │   │   rotates │
│         ├───┼───┼───┤   90°     │
│         │   │   │   │   smooth) │
│         └───┴───┴───┘           │
│                                 │
│   TRACK THE POSITIONS!          │
└─────────────────────────────────┘
```

### Phase 5: Player Turn
```
┌─────────────────────────────────┐
│ [⏸] 旋转网格 [❤❤❤]              │
├─────────────────────────────────┤
│ Level 1                          │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │   选择旋转后的位置            │ │
│ │   [Green indicator]          │ │
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│                                 │
│      ┌───┬───┬───┐              │
│      │   │ ✓ │   │  ✓ = Selected│
│      ├───┼───┼───┤  (Blue color)│
│      │ ✓ │   │   │              │
│      ├───┼───┼───┤  Click cells │
│      │   │   │ ✓ │  to select   │
│      └───┴───┴───┘              │
│                                 │
│  ┌─────────────────────────┐   │
│  │      提交答案 (Submit)   │   │
│  └─────────────────────────┘   │
└─────────────────────────────────┘
```

---

## 🔄 Rotation Examples

### Example 1: 90° Clockwise Rotation

**Before Rotation:**
```
┌───┬───┬───┐
│ ● │   │   │  Position (0,0) → ●
├───┼───┼───┤
│   │ ● │   │  Position (1,1) → ●
├───┼───┼───┤
│   │   │ ● │  Position (2,2) → ●
└───┴───┴───┘
```

**After 90° Rotation:**
```
┌───┬───┬───┐
│   │   │ ● │  Position (0,2) ← was (0,0)
├───┼───┼───┤
│   │ ● │   │  Position (1,1) ← was (1,1)
├───┼───┼───┤
│ ● │   │   │  Position (2,0) ← was (2,2)
└───┴───┴───┘
```

### Example 2: 180° Rotation

**Before Rotation:**
```
┌───┬───┬───┐
│ ● │ ● │   │
├───┼───┼───┤
│   │   │   │
├───┼───┼───┤
│   │   │ ● │
└───┴───┴───┘
```

**After 180° Rotation:**
```
┌───┬───┬───┐
│ ● │   │   │
├───┼───┼───┤
│   │   │   │
├───┼───┼───┤
│   │ ● │ ● │
└───┴───┴───┘
```

---

## 🎯 Rotation Angles

The game randomly selects from 6 rotation angles:

| Angle | Direction | Visual Effect |
|-------|-----------|---------------|
| **90°** | Clockwise | Top → Right |
| **-90°** | Counter-clockwise | Top → Left |
| **180°** | Half turn | Top → Bottom |
| **-180°** | Half turn (same) | Top → Bottom |
| **270°** | 3/4 clockwise | Top → Left |
| **-270°** | 3/4 counter-clockwise | Top → Right |

---

## 📊 Difficulty Progression

### Level 1-2: Easy (3x3 Grid)
```
┌───┬───┬───┐
│ ● │   │   │  3-4 cells to remember
├───┼───┼───┤  Small grid
│   │ ● │   │  Short memorization time
├───┼───┼───┤
│   │   │ ● │
└───┴───┴───┘
```

### Level 6-10: Medium (5x5 Grid)
```
┌───┬───┬───┬───┬───┐
│ ● │   │ ● │   │   │  8-12 cells
├───┼───┼───┼───┼───┤  Medium grid
│   │ ● │   │   │ ● │  Longer time
├───┼───┼───┼───┼───┤
│   │   │ ● │   │   │
├───┼───┼───┼───┼───┤
│ ● │   │   │ ● │   │
├───┼───┼───┼───┼───┤
│   │   │ ● │   │ ● │
└───┴───┴───┴───┴───┘
```

### Level 20+: Hard (8x8 Grid)
```
┌─┬─┬─┬─┬─┬─┬─┬─┐
│●│ │●│ │ │●│ │ │  24+ cells
├─┼─┼─┼─┼─┼─┼─┼─┤  Large grid
│ │●│ │ │●│ │●│ │  Maximum time
├─┼─┼─┼─┼─┼─┼─┼─┤
│●│ │ │●│ │ │ │●│
├─┼─┼─┼─┼─┼─┼─┼─┤
│ │ │●│ │●│ │ │ │
├─┼─┼─┼─┼─┼─┼─┼─┤
│ │●│ │ │ │●│ │●│
├─┼─┼─┼─┼─┼─┼─┼─┤
│●│ │ │●│ │ │●│ │
├─┼─┼─┼─┼─┼─┼─┼─┤
│ │ │●│ │●│ │ │●│
├─┼─┼─┼─┼─┼─┼─┼─┤
│ │●│ │ │ │●│ │ │
└─┴─┴─┴─┴─┴─┴─┴─┘
```

---

## 🎨 Color States

### Pattern Display (Teal)
```
┌───┬───┬───┐
│ 🟦│   │   │  Teal (#009688)
├───┼───┼───┤  Pulsing animation
│   │ 🟦│   │  "Remember these!"
├───┼───┼───┤
│   │   │ 🟦│
└───┴───┴───┘
```

### Player Selection (Blue)
```
┌───┬───┬───┐
│ 🔵│   │   │  Blue (#2196F3)
├───┼───┼───┤  Selected by player
│   │ 🔵│   │  Can deselect
├───┼───┼───┤
│   │   │ 🔵│
└───┴───┴───┘
```

### Correct Answer (Green)
```
┌───┬───┬───┐
│ 🟢│   │   │  Green (#4CAF50)
├───┼───┼───┤  Correct positions
│   │ 🟢│   │  +Score, next level
├───┼───┼───┤
│   │   │ 🟢│
└───┴───┴───┘
```

### Wrong Answer (Red)
```
┌───┬───┬───┐
│ 🔴│   │   │  Red (#F44336)
├───┼───┼───┤  Wrong positions
│   │ 🔴│   │  -1 life, retry
├───┼───┼───┤
│   │   │ 🔴│
└───┴───┴───┘
```

---

## 💡 Strategy Tips

### 1. Use Reference Points
```
Remember corner positions:
┌───┬───┬───┐
│ ● │   │ ● │  "Top corners"
├───┼───┼───┤
│   │   │   │
├───┼───┼───┤
│   │ ● │   │  "Bottom center"
└───┴───┴───┘
```

### 2. Visualize Rotation
```
Before:          After 90°:
● at top-left → ● at top-right
● at center   → ● at center
● at bottom   → ● at left
```

### 3. Group Patterns
```
Remember shapes:
┌───┬───┬───┐
│ ● │ ● │   │  "L shape"
├───┼───┼───┤
│ ● │   │   │
├───┼───┼───┤
│   │   │   │
└───┴───┴───┘
```

---

## 📈 Scoring Example

### Game Session
```
Level 1: 12 points  (10 + 1*2)
Level 2: 14 points  (10 + 2*2)
Level 3: 16 points  (10 + 3*2)
Level 4: 18 points  (10 + 4*2)
Level 5: 20 points  (10 + 5*2)
─────────────────────────────
Total:   80 points

Lives: ❤❤ (1 mistake)
Stars: ⭐ (reached level 5)
```

---

## 🎮 Controls

### Touch Interactions
- **Tap Cell**: Select/deselect cell
- **Tap Submit**: Submit answer
- **Tap Pause**: Pause game
- **Tap Back**: Exit game

### Visual Feedback
- **Cell Scale**: Selected cells shrink slightly
- **Cell Pulse**: Pattern cells pulse
- **Grid Rotation**: Smooth 2-second rotation
- **Color Change**: Immediate visual feedback

---

## ✅ Success Indicators

### Correct Answer
```
┌─────────────────────────────┐
│      正确！ (Correct!)       │
│      [Green indicator]      │
│      +12 points             │
│      Next level...          │
└─────────────────────────────┘
```

### Wrong Answer
```
┌─────────────────────────────┐
│      错误！ (Wrong!)         │
│      [Red indicator]        │
│      Lives: ❤❤ → ❤          │
│      Retry level...         │
└─────────────────────────────┘
```

### Game Over
```
┌─────────────────────────────┐
│      游戏结束 (Game Over)    │
│      [Red indicator]        │
│      Final Score: 80        │
│      Level Reached: 5       │
│      Stars: ⭐              │
└─────────────────────────────┘
```

---

**Game**: Rotating Grid (旋转网格)  
**Category**: Attention (注意力)  
**Difficulty**: Medium to Hard  
**Best For**: Spatial memory and mental rotation training

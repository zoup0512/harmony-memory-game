# 🎮 Phase 1: 5 Core Games Implementation Plan

## 📋 Overview
Implementing 5 core games representing each category, matching the old Java project exactly.

## 🎯 Games to Implement

### 1. Memory Grid (Game1MemoryGridActivity) ✅ Partially Done
- **Category**: Memory
- **ID**: "0"
- **Current Status**: Basic implementation exists, needs refactoring
- **Key Features**:
  - Rectangular grid (size increases with level)
  - Show pattern → Hide → Player selects → Validate
  - 3 lives system
  - GameProgression1 (grid size and cell count increase)
  - Animation: cells animate in, show challenge, hide, player input
  - Flow: Ready → GridAnimation → ShowChallenge → UserInput

### 2. Rotating Grid (Game2RotatingGridActivity) 🆕
- **Category**: Attention  
- **ID**: "1"
- **Key Features**:
  - Same as Memory Grid BUT grid rotates after showing pattern
  - Rotation angles: 90°, -180°, 270°, -90°, 180°, -270°
  - Player must remember positions after rotation
  - GameProgression2
  - Flow: Ready → GridAnimation → ShowChallenge → Rotation → UserInput
  - Uses ImageView snapshot for rotation animation

### 3. One and Only (Game10OneAndOnlyActivity) 🆕
- **Category**: Speed
- **ID**: "9"
- **Key Features**:
  - Find the unique element (different by color OR shape)
  - 60 second timer (45s for online, challenge varies)
  - No lives - time runs out = game over
  - Elements have color + shape (4 shapes, multiple colors)
  - GameProgression3
  - Max level: 46
  - Scoring: 8 base + level*2

### 4. Correctly? (Game11CorrectlyActivity) 🆕
- **Category**: Problem Solving
- **ID**: "10"
- **Key Features**:
  - Math equations - judge if correct or wrong
  - Two buttons: Correct / Wrong
  - 4-8 second timer per question
  - Equations get more complex with levels:
    - Level 1-3: Simple addition/subtraction (4-10 + 1-7)
    - Level 4-5: Larger numbers
    - Level 6-7: Multiplication
    - Level 8+: Multiple operations
  - GameProgression12
  - Scoring: 4 base + level*2

### 5. Colors (Game20ColorsActivity) 🆕
- **Category**: Flexibility
- **ID**: "19"
- **Key Features**:
  - Stroop effect: Color word vs text color
  - Two buttons: Match / Don't Match
  - 30 second timer total
  - GameProgression3
  - Shows "CORRECT!" hint on success
  - Scoring: 8 base (no level multiplier)

## 🏗️ Common Components Needed

### 1. BaseGameScreen ✅ Already Created
Located at: `app/src/main/kotlin/com/memory/brain/training/games/presentation/screens/game/common/BaseGameScreen.kt`

### 2. Game Progression Classes
Need to create Kotlin versions:
- `GameProgression1` - For Memory Grid (grid size increases)
- `GameProgression2` - For Rotating Grid (similar to Progression1)
- `GameProgression3` - For One and Only, Colors (element count increases)
- `GameProgression12` - For Correctly (equation complexity)

### 3. Grid Components
- `RectangularGrid` - Already have basic version, enhance it
- `Game10Grid` - For One and Only (elements with color+shape)
- `Game11Grid` - For Correctly (equation display)
- `Game20Grid` - For Colors (color word display)

### 4. Game State Management
```kotlin
sealed class GameFlowState {
    object Loading : GameFlowState()
    object Ready : GameFlowState()
    object ShowingPattern : GameFlowState()
    object Rotating : GameFlowState()
    object PlayerTurn : GameFlowState()
    object GameOver : GameFlowState()
}
```

## 📝 Implementation Checklist

### Game 1: Memory Grid (Refactor)
- [ ] Update ViewModel to match Game1MemoryGridActivity logic
- [ ] Implement GameProgression1
- [ ] Add proper game flow states
- [ ] Match UI exactly to main_activity.xml
- [ ] Add animations (cell animation, finish animation)
- [ ] Integrate with BaseGameScreen

### Game 2: Rotating Grid
- [ ] Create RotatingGridViewModel
- [ ] Create RotatingGridGameScreen
- [ ] Implement GameProgression2
- [ ] Add rotation animation logic
- [ ] Create grid snapshot for rotation
- [ ] Add rotation angles randomization

### Game 3: One and Only
- [ ] Create OneAndOnlyViewModel
- [ ] Create OneAndOnlyGameScreen
- [ ] Implement GameProgression3
- [ ] Create Element class (color + shape)
- [ ] Implement element generation logic
- [ ] Add 60-second timer
- [ ] Create shape rendering (4 shapes)

### Game 4: Correctly
- [ ] Create CorrectlyViewModel
- [ ] Create CorrectlyGameScreen
- [ ] Implement GameProgression12
- [ ] Create equation generation logic (7 types)
- [ ] Add per-question timer (4-8s)
- [ ] Create two-button UI (Correct/Wrong)

### Game 5: Colors
- [ ] Create ColorsViewModel
- [ ] Create ColorsGameScreen
- [ ] Implement GameProgression3
- [ ] Create color word generation
- [ ] Add Stroop effect logic
- [ ] Add 30-second timer
- [ ] Create two-button UI (Match/Don't Match)
- [ ] Add "CORRECT!" hint animation

## 🎨 UI Specifications

### Common Layout (from main_activity.xml)
```
┌─────────────────────────────────┐
│ [Pause] Game Name [❤❤❤]         │ <- panel_header
├─────────────────────────────────┤
│ Level X                          │ <- level_number
├─────────────────────────────────┤
│ [Progress Bar]                   │ <- progressBar (online/timed)
├─────────────────────────────────┤
│                                 │
│      Game Content Area           │ <- grid_container
│      (Grid/Buttons/etc)          │
│                                 │
├─────────────────────────────────┤
│ [Timer Overlay]                  │ <- timerContainer
└─────────────────────────────────┘
```

### Colors
- Background: `#E5F6FE` (light blue)
- Memory category: `#2196F3` (blue)
- Attention category: `#009688` (teal)
- Speed category: `#F44336` (red)
- Problem Solving: `#673AB7` (purple)
- Flexibility: `#FFC107` (amber)

## 🔄 Game Flow States

### Memory Grid & Rotating Grid
1. **Ready** (800ms) - Show "Ready!" text
2. **GridAnimation** (1000ms) - Cells animate in
3. **ShowChallenge** (1200ms + level*200ms) - Show pattern
4. **[Rotation]** (2000ms) - Only for Rotating Grid
5. **UserInput** - Player selects cells

### One and Only, Colors
1. **Start** - Show elements/word immediately
2. **PlayerInput** - Player clicks answer
3. **Next** - Generate new question

### Correctly
1. **ShowEquation** - Display equation
2. **PlayerInput** (4-8s timer) - Player answers
3. **Next** - Generate new equation

## 📊 Scoring System

### Memory Grid
- Base: 16 points
- Formula: 16 + (level * 2)

### Rotating Grid  
- Base: 10 points
- Formula: 10 + (level * 2)

### One and Only
- Base: 8 points
- Formula: 8 + (level * 2)

### Correctly
- Base: 4 points
- Formula: 4 + (level * 2)

### Colors
- Base: 8 points
- Formula: 8 (no level multiplier)

## 🚀 Implementation Order

1. **First**: Refactor Memory Grid (template for others)
2. **Second**: Implement Rotating Grid (similar to Memory Grid)
3. **Third**: Implement One and Only (different UI pattern)
4. **Fourth**: Implement Correctly (button-based UI)
5. **Fifth**: Implement Colors (similar to Correctly)

## 📁 File Structure

```
app/src/main/kotlin/com/memory/brain/training/games/
├── domain/
│   └── model/
│       ├── GameProgression1.kt
│       ├── GameProgression2.kt
│       ├── GameProgression3.kt
│       └── GameProgression12.kt
├── presentation/
│   └── screens/
│       └── game/
│           ├── common/
│           │   ├── BaseGameScreen.kt ✅
│           │   └── GameFlowState.kt
│           └── games/
│               ├── memorygrid/
│               │   ├── MemoryGridViewModel.kt
│               │   └── MemoryGridGameScreen.kt
│               ├── rotatinggrid/
│               │   ├── RotatingGridViewModel.kt
│               │   └── RotatingGridGameScreen.kt
│               ├── oneandonly/
│               │   ├── OneAndOnlyViewModel.kt
│               │   ├── OneAndOnlyGameScreen.kt
│               │   └── Element.kt
│               ├── correctly/
│               │   ├── CorrectlyViewModel.kt
│               │   └── CorrectlyGameScreen.kt
│               └── colors/
│                   ├── ColorsViewModel.kt
│                   └── ColorsGameScreen.kt
```

## ✅ Success Criteria

Each game must have:
- [x] Exact game logic from old project
- [x] UI matching main_activity.xml layout
- [x] Proper progression system
- [x] 3 lives system (or timer for timed games)
- [x] Score calculation matching old project
- [x] Animations matching old project
- [x] Integration with GameFactory
- [x] Navigation to result screen

---

**Status**: Ready to implement
**Start Date**: 2026-05-01
**Estimated Time**: 10-20 hours for all 5 games

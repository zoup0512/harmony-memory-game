export {}

const PAGE_BG = '#F3F8FF';
const CARD_BG = '#FFFFFF';
const TEXT_MAIN = '#1F2937';
const TEXT_SUB = '#6B7280';
const BORDER_SOFT = '#E5E7EB';
const THEME = '#5B8DEF';
const DANGER = '#EF5B5B';
const SUCCESS = '#36B37E';

// ─── Utility helpers ──────────────────────────────────────────────────────────

function makeRange(n: number): number[] {
  const arr: number[] = [];
  for (let i = 0; i < n; i++) arr.push(i);
  return arr;
}

function repeatStr(s: string, n: number, sep: string): string {
  const parts: string[] = [];
  for (let i = 0; i < n; i++) parts.push(s);
  return parts.join(sep);
}

// ─── Game constants ────────────────────────────────────────────────────────────

const STATE_READY = 'READY';
const STATE_GRID_ANIM = 'GRID_ANIMATION';
const STATE_SHOW_CHALLENGE = 'SHOW_CHALLENGE';
const STATE_USER_INPUT = 'USER_INPUT';
const STATE_SUCCESS = 'SUCCESS_ANIMATION';
const STATE_FAILURE = 'FAILURE_ANIMATION';
const STATE_GAME_OVER = 'GAME_OVER';

// ─── Progression helpers ────────────────────────────────────────────────────────

function calcGridSize(level: number): number {
  if (level <= 2) return 3;
  if (level <= 5) return 4;
  if (level <= 10) return 5;
  if (level <= 15) return 6;
  if (level <= 20) return 7;
  return 8;
}

function calcWinCells(level: number, gridSize: number): number {
  let w: number;
  if (level === 1) w = 3;
  else if (level === 2) w = 4;
  else if (level === 3) w = 5;
  else if (level === 4) w = 6;
  else if (level === 5) w = 7;
  else if (level === 6) w = 8;
  else if (level === 7) w = 9;
  else if (level === 8) w = 10;
  else if (level === 9) w = 11;
  else if (level === 10) w = 12;
  else if (level <= 15) w = 13 + (level - 10);
  else if (level <= 20) w = 18 + (level - 15);
  else w = 23 + (level - 20);
  const max = gridSize * gridSize - 1;
  return w > max ? max : w;
}

function calcStars(level: number): number {
  if (level >= 15) return 3;
  if (level >= 11) return 2;
  if (level >= 4) return 1;
  return 0;
}

function generatePattern(gridSize: number, winCells: number): number[] {
  const total = gridSize * gridSize;
  const positions: number[] = [];
  while (positions.length < winCells) {
    const n = Math.floor(Math.random() * total);
    if (!positions.includes(n)) {
      positions.push(n);
    }
  }
  return positions;
}

// ─── Color helpers ──────────────────────────────────────────────────────────────

function stateColor(state: string): string {
  switch (state) {
    case STATE_READY: return THEME;
    case STATE_GRID_ANIM: return '#7C5CFF';
    case STATE_SHOW_CHALLENGE: return '#FFB020';
    case STATE_USER_INPUT: return SUCCESS;
    case STATE_SUCCESS: return SUCCESS;
    case STATE_FAILURE: return DANGER;
    case STATE_GAME_OVER: return DANGER;
    default: return THEME;
  }
}

function stateLabel(state: string): string {
  switch (state) {
    case STATE_READY: return '准备好！';
    case STATE_GRID_ANIM: return '注意...';
    case STATE_SHOW_CHALLENGE: return '记住这些位置！';
    case STATE_USER_INPUT: return '点击你记住的位置';
    case STATE_SUCCESS: return '正确！';
    case STATE_FAILURE: return '错误！';
    case STATE_GAME_OVER: return '游戏结束';
    default: return '';
  }
}

// ─── Grid Cell Component ────────────────────────────────────────────────────────

@Component
struct GridCell {
  @Prop cellIndex: number = 0;
  @Prop isInPattern: boolean = false;
  @Prop isSelected: boolean = false;
  @Prop gameState: string = STATE_READY;
  onCellClick: (pos: number) => void = () => {};

  private showPattern(): boolean {
    return (this.gameState === STATE_SHOW_CHALLENGE ||
      this.gameState === STATE_SUCCESS ||
      this.gameState === STATE_FAILURE) && this.isInPattern;
  }

  private showSelected(): boolean {
    return this.gameState === STATE_USER_INPUT && this.isSelected;
  }

  private cellBgColor(): string {
    if (this.showPattern()) return SUCCESS;
    if (this.showSelected()) return THEME;
    return '#E5E7EB';
  }

  build() {
    Stack() {
      // Cell background
      Column()
        .width('100%')
        .height('100%')
        .backgroundColor(this.cellBgColor())
        .borderRadius(10)
        .border({
          width: (this.showPattern() || this.showSelected()) ? 2 : 0,
          color: '#FFFFFF'
        })
        .animation({
          duration: 200,
          curve: Curve.EaseOut
        })

      // Checkmark icon (shown when pattern or selected)
      if (this.showPattern() || this.showSelected()) {
        Text('✓')
          .fontSize(20)
          .fontColor('#FFFFFF')
          .fontWeight(FontWeight.Bold)
      }
    }
    .width('100%')
    .height('100%')
    .onClick(() => {
      if (this.gameState === STATE_USER_INPUT) {
        this.onCellClick(this.cellIndex);
      }
    })
  }
}

// ─── Game State Indicator ───────────────────────────────────────────────────────

@Component
struct GameStateIndicator {
  @Prop gameState: string = STATE_READY;

  build() {
    Text(stateLabel(this.gameState))
      .fontSize(22)
      .fontWeight(FontWeight.Bold)
      .fontColor(stateColor(this.gameState))
      .textAlign(TextAlign.Center)
      .width('100%')
      .padding({ top: 14, bottom: 14, left: 16, right: 16 })
      .backgroundColor(this.tintBg(stateColor(this.gameState)))
      .borderRadius(18)
      .shadow({ radius: 8, color: '#12000000', offsetX: 0, offsetY: 3 })
      .scale({
        x: (this.gameState === STATE_SUCCESS || this.gameState === STATE_FAILURE) ? 1.08 : 1.0,
        y: (this.gameState === STATE_SUCCESS || this.gameState === STATE_FAILURE) ? 1.08 : 1.0
      })
      .animation({ duration: 250, curve: Curve.FastOutSlowIn })
  }

  private tintBg(hex: string): string {
    const r = parseInt(hex.slice(1, 3), 16);
    const g = parseInt(hex.slice(3, 5), 16);
    const b = parseInt(hex.slice(5, 7), 16);
    return `rgba(${r},${g},${b},0.12)`;
  }
}

// ─── Timer Overlay ──────────────────────────────────────────────────────────────

@Component
struct TimerOverlay {
  @Prop visible: boolean = false;
  @Prop timerSeconds: number = 3;

  build() {
    if (this.visible) {
      Stack() {
        // Semi-transparent background
        Column()
          .width('100%')
          .height('100%')
          .backgroundColor('rgba(243,248,255,0.92)')

        Column({ space: 20 }) {
          // Circular countdown
          Stack() {
            Circle()
              .width(120)
              .height(120)
              .fill('#FFFFFF')
              .shadow({ radius: 8, color: '#2A000000', offsetX: 0, offsetY: 4 })

            Text(this.timerSeconds > 0 ? this.timerSeconds.toString() : '✓')
              .fontSize(52)
              .fontWeight(FontWeight.Bold)
              .fontColor(SUCCESS)
          }
          .width(120)
          .height(120)

          Text('准备好！')
            .fontSize(24)
            .fontWeight(FontWeight.Bold)
            .fontColor(SUCCESS)
        }
        .justifyContent(FlexAlign.Center)
        .alignItems(HorizontalAlign.Center)
      }
      .width('100%')
      .height('100%')
    }
  }
}

// ─── Pause Dialog ───────────────────────────────────────────────────────────────

@Component
struct PauseDialog {
  @Prop visible: boolean = false;
  onResume: () => void = () => {};
  onRestart: () => void = () => {};
  onExit: () => void = () => {};

  build() {
    if (this.visible) {
      Stack() {
        // Dim background
        Column()
          .width('100%')
          .height('100%')
          .backgroundColor('rgba(15,23,42,0.45)')
          .onClick(() => { /* block tap-through */ })

        // Dialog card
        Column({ space: 16 }) {
          Text('游戏暂停')
            .fontSize(24)
            .fontWeight(FontWeight.Bold)
            .fontColor(TEXT_MAIN)

          Text('记忆网格')
            .fontSize(16)
            .fontColor(TEXT_SUB)

          Divider()
            .color(BORDER_SOFT)
            .margin({ top: 4, bottom: 4 })

          // Resume button
          Button({ type: ButtonType.Normal }) {
            Row({ space: 10 }) {
              Text('▶')
                .fontSize(18)
                .fontColor('#FFFFFF')
              Text('继续游戏')
                .fontSize(18)
                .fontWeight(FontWeight.Medium)
                .fontColor('#FFFFFF')
            }
            .justifyContent(FlexAlign.Center)
          }
          .width('100%')
          .height(56)
          .backgroundColor(SUCCESS)
          .borderRadius(12)
          .onClick(() => this.onResume())

          // Restart button
          Button({ type: ButtonType.Normal }) {
            Row({ space: 10 }) {
              Text('↺')
                .fontSize(18)
                .fontColor('#FFFFFF')
              Text('重新开始')
                .fontSize(18)
                .fontWeight(FontWeight.Medium)
                .fontColor('#FFFFFF')
            }
            .justifyContent(FlexAlign.Center)
          }
          .width('100%')
          .height(56)
          .backgroundColor(THEME)
          .borderRadius(12)
          .onClick(() => this.onRestart())

          // Exit button
          Button({ type: ButtonType.Normal }) {
            Row({ space: 10 }) {
              Text('✕')
                .fontSize(18)
                .fontColor('#FFFFFF')
              Text('退出游戏')
                .fontSize(18)
                .fontWeight(FontWeight.Medium)
                .fontColor('#FFFFFF')
            }
            .justifyContent(FlexAlign.Center)
          }
          .width('100%')
          .height(56)
          .backgroundColor(DANGER)
          .borderRadius(12)
          .onClick(() => this.onExit())
        }
        .padding(24)
        .backgroundColor(CARD_BG)
        .borderRadius(24)
        .shadow({ radius: 24, color: '#26000000', offsetX: 0, offsetY: 10 })
        .width('85%')
        .alignItems(HorizontalAlign.Center)
      }
      .width('100%')
      .height('100%')
      .position({ x: 0, y: 0 })
      .zIndex(100)
    }
  }
}

// ─── Game Over Overlay ──────────────────────────────────────────────────────────

@Component
struct GameOverOverlay {
  @Prop visible: boolean = false;
  @Prop score: number = 0;
  @Prop level: number = 1;
  @Prop stars: number = 0;
  onPlayAgain: () => void = () => {};
  onExit: () => void = () => {};

  build() {
    if (this.visible) {
      Stack() {
        Column()
          .width('100%')
          .height('100%')
          .backgroundColor('rgba(15,23,42,0.50)')

        Column({ space: 20 }) {
          Text('游戏结束')
            .fontSize(32)
            .fontWeight(FontWeight.Bold)
            .fontColor(TEXT_MAIN)

          // Stars display
          Row({ space: 8 }) {
            Text(this.stars >= 1 ? '★' : '☆')
              .fontSize(40)
              .fontColor(this.stars >= 1 ? '#FFC83D' : '#CBD5E1')
            Text(this.stars >= 2 ? '★' : '☆')
              .fontSize(40)
              .fontColor(this.stars >= 2 ? '#FFC83D' : '#CBD5E1')
            Text(this.stars >= 3 ? '★' : '☆')
              .fontSize(40)
              .fontColor(this.stars >= 3 ? '#FFC83D' : '#CBD5E1')
          }

          // Stats
          Column({ space: 10 }) {
            Row({ space: 8 }) {
              Text('最终得分：')
                .fontSize(18)
                .fontColor(TEXT_SUB)
              Text(this.score.toString())
                .fontSize(24)
                .fontWeight(FontWeight.Bold)
                .fontColor(THEME)
            }

            Row({ space: 8 }) {
              Text('到达关卡：')
                .fontSize(18)
                .fontColor(TEXT_SUB)
              Text(this.level.toString())
                .fontSize(24)
                .fontWeight(FontWeight.Bold)
                .fontColor(SUCCESS)
            }
          }
          .padding({ top: 8, bottom: 8 })

          // Play again button
          Button({ type: ButtonType.Normal }) {
            Text('再玩一次')
              .fontSize(20)
              .fontWeight(FontWeight.Bold)
              .fontColor('#FFFFFF')
          }
          .width('100%')
          .height(60)
          .backgroundColor(SUCCESS)
          .borderRadius(14)
          .onClick(() => this.onPlayAgain())

          // Exit button
          Button({ type: ButtonType.Normal }) {
            Text('退出游戏')
              .fontSize(18)
              .fontColor('#FFFFFF')
          }
          .width('100%')
          .height(52)
          .backgroundColor(DANGER)
          .borderRadius(14)
          .onClick(() => this.onExit())
        }
        .padding(28)
        .backgroundColor(CARD_BG)
        .borderRadius(24)
        .shadow({ radius: 24, color: '#26000000', offsetX: 0, offsetY: 10 })
        .width('85%')
        .alignItems(HorizontalAlign.Center)
      }
      .width('100%')
      .height('100%')
      .position({ x: 0, y: 0 })
      .zIndex(200)
    }
  }
}

// ─── Main Game Page ─────────────────────────────────────────────────────────────

@Entry
@Component
struct MemoryGridPage {
  // ── reactive state ──────────────────────────────────
  @State private gameState: string = STATE_READY;
  @State private level: number = 1;
  @State private score: number = 0;
  @State private lives: number = 3;
  @State private gridSize: number = 3;
  @State private winCells: number = 3;
  @State private pattern: number[] = [];
  @State private userSelections: number[] = [];
  @State private showReadyTimer: boolean = false;
  @State private readyTimerSeconds: number = 3;
  @State private isPaused: boolean = false;
  @State private isGameOver: boolean = false;

  // ── flow control ─────────────────────────────────────
  private flowId: number = 0;

  // ── lifecycle ─────────────────────────────────────────
  aboutToAppear(): void {
    this.startGame();
  }

  // ─── Game control ──────────────────────────────────────────────────────────────

  private startGame(): void {
    this.level = 1;
    this.score = 0;
    this.lives = 3;
    this.isPaused = false;
    this.isGameOver = false;
    this.userSelections = [];
    this.updateGridParams();
    this.startGameFlow();
  }

  private updateGridParams(): void {
    this.gridSize = calcGridSize(this.level);
    this.winCells = calcWinCells(this.level, this.gridSize);
  }

  private startGameFlow(): void {
    this.flowId = this.flowId + 1;
    const myFlow = this.flowId;
    this.runFlow(myFlow);
  }

  private async runFlow(flowId: number): Promise<void> {
    const alive = (): boolean => this.flowId === flowId && !this.isPaused;
    const sleep = (ms: number): Promise<void> =>
      new Promise(resolve => setTimeout(resolve, ms));

    // ── READY: 3-2-1 countdown (800ms total) ────────────
    this.gameState = STATE_READY;
    this.showReadyTimer = true;
    this.readyTimerSeconds = 3;

    await sleep(267);
    if (!alive()) return;
    this.readyTimerSeconds = 2;

    await sleep(267);
    if (!alive()) return;
    this.readyTimerSeconds = 1;

    await sleep(266);
    if (!alive()) return;
    this.readyTimerSeconds = 0;
    await sleep(200);
    if (!alive()) return;
    this.showReadyTimer = false;

    // ── GRID_ANIMATION: cells animate in (1000ms) ───────
    this.gameState = STATE_GRID_ANIM;
    await sleep(1000);
    if (!alive()) return;

    // ── SHOW_CHALLENGE: reveal pattern ──────────────────
    this.doGeneratePattern();
    this.gameState = STATE_SHOW_CHALLENGE;
    const showDuration = 1200 + this.level * 200;
    await sleep(showDuration);
    if (!alive()) return;

    // ── USER_INPUT: wait for taps ────────────────────────
    this.userSelections = [];
    this.gameState = STATE_USER_INPUT;
  }

  private doGeneratePattern(): void {
    this.pattern = generatePattern(this.gridSize, this.winCells);
    this.userSelections = [];
  }

  // ─── Cell click handler ────────────────────────────────────────────────────────

  private onCellClick(position: number): void {
    if (this.gameState !== STATE_USER_INPUT) return;

    const selections = [...this.userSelections];

    if (selections.includes(position)) {
      // Deselect
      const idx = selections.indexOf(position);
      selections.splice(idx, 1);
      this.userSelections = selections;
      return;
    }

    // Select
    if (this.pattern.includes(position)) {
      // Correct cell
      selections.push(position);
      this.userSelections = selections;
      if (selections.length === this.winCells) {
        this.onSuccess();
      }
    } else {
      // Wrong cell
      this.onFailure();
    }
  }

  private onSuccess(): void {
    const levelScore = 16 + this.level * 2;
    this.score = this.score + levelScore;
    this.gameState = STATE_SUCCESS;

    setTimeout(() => {
      if (this.isGameOver || this.isPaused) return;
      this.level = this.level + 1;
      this.updateGridParams();
      this.startGameFlow();
    }, 500);
  }

  private onFailure(): void {
    this.lives = this.lives - 1;
    if (this.lives <= 0) {
      this.lives = 0;
      this.gameState = STATE_GAME_OVER;
      this.flowId = this.flowId + 1; // cancel any running flow
      this.isGameOver = true;
    } else {
      this.gameState = STATE_FAILURE;
      setTimeout(() => {
        if (this.isPaused) return;
        this.startGameFlow();
      }, 1000);
    }
  }

  // ─── Pause / resume ────────────────────────────────────────────────────────────

  private pauseGame(): void {
    if (this.isGameOver) return;
    this.flowId = this.flowId + 1; // cancel running flow
    this.isPaused = true;
  }

  private resumeGame(): void {
    this.isPaused = false;
    if (this.gameState === STATE_USER_INPUT) {
      // stay in user input — player continues clicking
    } else {
      this.startGameFlow();
    }
  }

  private restartGame(): void {
    this.isPaused = false;
    this.startGame();
  }

  private exitGame(): void {
    this.getUIContext().getRouter().back();
  }

  // ─── Build ─────────────────────────────────────────────────────────────────────

  build() {
    Stack() {
      // ── Main content ─────────────────────────────────
      Column() {
        // ── Top bar ──────────────────────────────────────
        Row() {
          // Pause button (left)
          Button({ type: ButtonType.Circle }) {
            Text('⏸')
              .fontSize(20)
              .fontColor(TEXT_MAIN)
          }
          .width(44)
          .height(44)
          .backgroundColor('rgba(255,255,255,0.82)')
          .onClick(() => this.pauseGame())

          Blank()

          // Game name (center)
          Text('记忆网格')
            .fontSize(19)
            .fontColor(TEXT_MAIN)
            .fontWeight(FontWeight.Bold)

          Blank()

          // Lives (right)
          Row({ space: 4 }) {
            ForEach(
              [0, 1, 2] as number[],
              (i: number) => {
                Text(i < this.lives ? '❤' : '♡')
                  .fontSize(22)
                  .fontColor(i < this.lives ? DANGER : '#CBD5E1')
              },
              (i: number) => `life_${i}`
            )
          }
        }
        .width('100%')
        .height(64)
        .padding({ left: 14, right: 14 })
        .alignItems(VerticalAlign.Center)

        // ── Level indicator ───────────────────────────────
        Text(`关卡 ${this.level}`)
          .fontSize(14)
          .fontColor(TEXT_SUB)
          .fontWeight(FontWeight.Bold)
          .padding({ left: 14, right: 14, top: 6, bottom: 6 })
          .backgroundColor('rgba(255,255,255,0.76)')
          .borderRadius(999)
          .alignSelf(ItemAlign.Center)
          .margin({ bottom: 8 })

        // ── Game content area ─────────────────────────────
        Stack() {
          Column({ space: 20 }) {
            // State indicator
            GameStateIndicator({ gameState: this.gameState })

            // Grid
            this.buildGrid()
          }
          .width('100%')
          .height('100%')
          .justifyContent(FlexAlign.Center)
          .alignItems(HorizontalAlign.Center)
          .padding({ top: 18, bottom: 18, left: 18, right: 18 })

          // Timer overlay (READY phase)
          TimerOverlay({
            visible: this.showReadyTimer,
            timerSeconds: this.readyTimerSeconds
          })
        }
        .layoutWeight(1)
        .width('100%')
      }
      .width('100%')
      .height('100%')
      .backgroundColor(PAGE_BG)

      // ── Pause dialog (above everything) ──────────────
      PauseDialog({
        visible: this.isPaused,
        onResume: () => this.resumeGame(),
        onRestart: () => this.restartGame(),
        onExit: () => this.exitGame()
      })

      // ── Game over overlay ─────────────────────────────
      GameOverOverlay({
        visible: this.isGameOver,
        score: this.score,
        level: this.level,
        stars: calcStars(this.level),
        onPlayAgain: () => this.startGame(),
        onExit: () => this.exitGame()
      })
    }
    .width('100%')
    .height('100%')
  }

  // ─── Grid builder ──────────────────────────────────────────────────────────────

  @Builder
  buildGrid() {
    Grid() {
      ForEach(
        makeRange(this.gridSize * this.gridSize),
        (pos: number) => {
          GridItem() {
            GridCell({
              cellIndex: pos,
              isInPattern: this.pattern.includes(pos),
              isSelected: this.userSelections.includes(pos),
              gameState: this.gameState,
              onCellClick: (p: number) => this.onCellClick(p)
            })
          }
        },
        (pos: number) => `${this.flowId}_${pos}`
      )
    }
    .columnsTemplate(repeatStr('1fr', this.gridSize, ' '))
    .rowsTemplate(repeatStr('1fr', this.gridSize, ' '))
    .columnsGap(7)
    .rowsGap(7)
    .width('100%')
    .aspectRatio(1)
    .padding(12)
    .backgroundColor(CARD_BG)
    .borderRadius(24)
    .shadow({ radius: 14, color: '#14000000', offsetX: 0, offsetY: 6 })
  }
}

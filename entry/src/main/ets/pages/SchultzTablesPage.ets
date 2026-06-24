export {}

const PAGE_BG = '#F3F8FF';
const CARD_BG = '#FFFFFF';
const TEXT_MAIN = '#1F2937';
const TEXT_SUB = '#6B7280';
const BORDER_SOFT = '#E5E7EB';
const THEME = '#00A896';
const DANGER = '#EF5B5B';
const SUCCESS = '#36B37E';

// ─── Constants ─────────────────────────────────────────────────────────────────

const TOTAL_NUMBERS = 48;
const COLS = 6;
const GAME_DURATION_MS = 50000;

const CELL_COLORS: string[] = [
  '#E91E63', // Pink
  '#9C27B0', // Purple
  '#3F51B5', // Indigo
  '#2196F3', // Blue
  '#00BCD4', // Cyan
  '#4CAF50', // Green
  '#FFEB3B', // Yellow
  '#FF9800', // Orange
  '#FF5722', // Deep Orange
  '#795548', // Brown
];

// ─── Data type ─────────────────────────────────────────────────────────────────

interface NumberElement {
  num: number;
  color: string;
  isClicked: boolean;
}

// ─── Helpers ───────────────────────────────────────────────────────────────────

function shuffleColors(): string[] {
  const arr = [...CELL_COLORS];
  for (let i = arr.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    const tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
  return arr;
}

function generateElements(): NumberElement[] {
  const colors = shuffleColors();
  const elements: NumberElement[] = [];
  for (let i = 0; i < TOTAL_NUMBERS; i++) {
    elements.push({
      num: i + 1,
      color: colors[i % colors.length],
      isClicked: false,
    });
  }
  // Fisher-Yates shuffle
  for (let i = elements.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    const tmp = elements[i];
    elements[i] = elements[j];
    elements[j] = tmp;
  }
  return elements;
}

function calcStars(level: number): number {
  if (level >= 20) return 3;
  if (level >= 10) return 2;
  return 1;
}

function hexWithAlpha(hex: string, alpha: number): string {
  const r = parseInt(hex.slice(1, 3), 16);
  const g = parseInt(hex.slice(3, 5), 16);
  const b = parseInt(hex.slice(5, 7), 16);
  return `rgba(${r},${g},${b},${alpha})`;
}

// ─── Number cell component ─────────────────────────────────────────────────────

@Component
struct NumberCell {
  @Prop num: number = 0;
  @Prop color: string = THEME;
  @Prop isClicked: boolean = false;
  @Prop showWrongFeedback: boolean = false;
  onTap: (n: number) => void = () => {};

  private bgColor(): string {
    if (this.isClicked) return hexWithAlpha(this.color, 0.3);
    return this.color;
  }

  private cellBorderColor(): string {
    if (this.isClicked) return SUCCESS;
    if (this.showWrongFeedback) return DANGER;
    return 'transparent';
  }

  private textColor(): string {
    return this.isClicked ? TEXT_SUB : '#FFFFFF';
  }

  build() {
    Stack() {
      Column()
        .width('100%')
        .height('100%')
        .backgroundColor(this.bgColor())
        .borderRadius(8)
        .border({
          width: this.isClicked || this.showWrongFeedback ? 2 : 0,
          color: this.cellBorderColor()
        })
        .animation({ duration: 150, curve: Curve.EaseOut })
        .scale({
          x: this.isClicked ? 0.82 : 1.0,
          y: this.isClicked ? 0.82 : 1.0
        })
        .animation({ duration: 150, curve: Curve.EaseOut })

      Text(this.num.toString())
        .fontSize(16)
        .fontWeight(FontWeight.Bold)
        .fontColor(this.textColor())
    }
    .width('100%')
    .aspectRatio(1)
    .onClick(() => {
      if (!this.isClicked) {
        this.onTap(this.num);
      }
    })
  }
}

// ─── Pause dialog ──────────────────────────────────────────────────────────────

@Component
struct STPauseDialog {
  @Prop visible: boolean = false;
  onResume: () => void = () => {};
  onRestart: () => void = () => {};
  onExit: () => void = () => {};

  build() {
    if (this.visible) {
      Stack() {
        Column()
          .width('100%').height('100%')
          .backgroundColor('rgba(15,23,42,0.45)')

        Column({ space: 14 }) {
          Text('游戏暂停')
            .fontSize(22).fontWeight(FontWeight.Bold).fontColor(TEXT_MAIN)
          Text('舒尔特表')
            .fontSize(14).fontColor(TEXT_SUB)
          Divider().color(BORDER_SOFT)

          Button({ type: ButtonType.Normal }) {
            Row({ space: 8 }) {
              Text('▶').fontSize(16).fontColor('#FFFFFF')
              Text('继续游戏').fontSize(16).fontColor('#FFFFFF')
            }.justifyContent(FlexAlign.Center)
          }
          .width('100%').height(52).backgroundColor(SUCCESS).borderRadius(10)
          .onClick(() => this.onResume())

          Button({ type: ButtonType.Normal }) {
            Row({ space: 8 }) {
              Text('↺').fontSize(16).fontColor('#FFFFFF')
              Text('重新开始').fontSize(16).fontColor('#FFFFFF')
            }.justifyContent(FlexAlign.Center)
          }
          .width('100%').height(52).backgroundColor(THEME).borderRadius(10)
          .onClick(() => this.onRestart())

          Button({ type: ButtonType.Normal }) {
            Row({ space: 8 }) {
              Text('✕').fontSize(16).fontColor('#FFFFFF')
              Text('退出游戏').fontSize(16).fontColor('#FFFFFF')
            }.justifyContent(FlexAlign.Center)
          }
          .width('100%').height(52).backgroundColor(DANGER).borderRadius(10)
          .onClick(() => this.onExit())
        }
        .padding(24).backgroundColor(CARD_BG).borderRadius(24)
        .shadow({ radius: 24, color: '#26000000', offsetX: 0, offsetY: 10 })
        .width('85%').alignItems(HorizontalAlign.Center)
      }
      .width('100%').height('100%')
      .position({ x: 0, y: 0 })
      .zIndex(100)
    }
  }
}

// ─── Game over overlay ─────────────────────────────────────────────────────────

@Component
struct STGameOverOverlay {
  @Prop visible: boolean = false;
  @Prop score: number = 0;
  @Prop level: number = 1;
  @Prop stars: number = 1;
  onPlayAgain: () => void = () => {};
  onExit: () => void = () => {};

  build() {
    if (this.visible) {
      Stack() {
        Column()
          .width('100%').height('100%')
          .backgroundColor('rgba(15,23,42,0.50)')

        Column({ space: 18 }) {
          Text('游戏结束')
            .fontSize(30).fontWeight(FontWeight.Bold).fontColor(TEXT_MAIN)

          Row({ space: 6 }) {
            Text(this.stars >= 1 ? '★' : '☆').fontSize(38)
              .fontColor(this.stars >= 1 ? '#FFC83D' : '#CBD5E1')
            Text(this.stars >= 2 ? '★' : '☆').fontSize(38)
              .fontColor(this.stars >= 2 ? '#FFC83D' : '#CBD5E1')
            Text(this.stars >= 3 ? '★' : '☆').fontSize(38)
              .fontColor(this.stars >= 3 ? '#FFC83D' : '#CBD5E1')
          }

          Column({ space: 8 }) {
            Row({ space: 6 }) {
              Text('最终得分：').fontSize(16).fontColor(TEXT_SUB)
              Text(this.score.toString()).fontSize(22).fontWeight(FontWeight.Bold)
                .fontColor(THEME)
            }
            Row({ space: 6 }) {
              Text('完成关卡：').fontSize(16).fontColor(TEXT_SUB)
              Text((this.level - 1).toString()).fontSize(22).fontWeight(FontWeight.Bold)
                .fontColor(SUCCESS)
            }
          }

          Button({ type: ButtonType.Normal }) {
            Text('再玩一次').fontSize(18).fontWeight(FontWeight.Bold).fontColor('#FFFFFF')
          }
          .width('100%').height(56).backgroundColor(SUCCESS).borderRadius(12)
          .onClick(() => this.onPlayAgain())

          Button({ type: ButtonType.Normal }) {
            Text('退出游戏').fontSize(16).fontColor('#FFFFFF')
          }
          .width('100%').height(48).backgroundColor(DANGER).borderRadius(12)
          .onClick(() => this.onExit())
        }
        .padding(28).backgroundColor(CARD_BG).borderRadius(24)
        .shadow({ radius: 24, color: '#26000000', offsetX: 0, offsetY: 10 })
        .width('85%').alignItems(HorizontalAlign.Center)
      }
      .width('100%').height('100%')
      .position({ x: 0, y: 0 })
      .zIndex(200)
    }
  }
}

// ─── Main page ─────────────────────────────────────────────────────────────────

@Entry
@Component
struct SchultzTablesPage {
  @State private elements: NumberElement[] = [];
  @State private currentNumber: number = 1;
  @State private level: number = 1;
  @State private lives: number = 3;
  @State private timeRemainingMs: number = GAME_DURATION_MS;
  @State private isPlaying: boolean = false;
  @State private isGameOver: boolean = false;
  @State private isPaused: boolean = false;
  @State private showWrongFeedback: boolean = false;

  private timerId: number = -1;
  private wrongFeedbackId: number = -1;

  // ── Lifecycle ──────────────────────────────────────
  aboutToAppear(): void {
    this.startGame();
  }

  aboutToDisappear(): void {
    this.clearTimer();
    if (this.wrongFeedbackId !== -1) {
      clearTimeout(this.wrongFeedbackId);
    }
  }

  // ── Timer ──────────────────────────────────────────
  private clearTimer(): void {
    if (this.timerId !== -1) {
      clearInterval(this.timerId);
      this.timerId = -1;
    }
  }

  private startTimer(): void {
    this.clearTimer();
    this.timerId = setInterval(() => {
      this.timeRemainingMs = this.timeRemainingMs - 100;
      if (this.timeRemainingMs <= 0) {
        this.timeRemainingMs = 0;
        this.clearTimer();
        this.isGameOver = true;
        this.isPlaying = false;
      }
    }, 100);
  }

  // ── Game control ───────────────────────────────────
  private startGame(): void {
    this.clearTimer();
    this.level = 1;
    this.lives = 3;
    this.timeRemainingMs = GAME_DURATION_MS;
    this.isGameOver = false;
    this.isPaused = false;
    this.showWrongFeedback = false;
    this.currentNumber = 1;
    this.elements = generateElements();
    this.isPlaying = true;
    this.startTimer();
  }

  private startLevel(): void {
    this.currentNumber = 1;
    this.showWrongFeedback = false;
    this.elements = generateElements();
  }

  // ── Number click ───────────────────────────────────
  private onNumberTapped(num: number): void {
    if (!this.isPlaying || this.isGameOver || this.isPaused) return;

    const el = this.elements.find((e: NumberElement) => e.num === num);
    if (!el || el.isClicked) return;

    if (num === this.currentNumber) {
      // Correct
      const updated = this.elements.map((e: NumberElement): NumberElement => {
        if (e.num === num) {
          return { num: e.num, color: e.color, isClicked: true };
        }
        return e;
      });
      this.elements = updated;
      this.currentNumber = this.currentNumber + 1;

      if (this.currentNumber > TOTAL_NUMBERS) {
        // Level complete
        this.level = this.level + 1;
        setTimeout(() => {
          if (!this.isGameOver && !this.isPaused) {
            this.startLevel();
          }
        }, 500);
      }
    } else {
      // Wrong click
      this.showWrongFeedback = true;
      if (this.wrongFeedbackId !== -1) clearTimeout(this.wrongFeedbackId);
      this.wrongFeedbackId = setTimeout(() => {
        this.showWrongFeedback = false;
        const newLives = this.lives - 1;
        this.lives = newLives;
        if (newLives <= 0) {
          this.lives = 0;
          this.clearTimer();
          this.isGameOver = true;
          this.isPlaying = false;
        } else {
          this.startLevel();
        }
      }, 800);
    }
  }

  // ── Pause / resume ─────────────────────────────────
  private pauseGame(): void {
    if (this.isGameOver) return;
    this.clearTimer();
    this.isPaused = true;
  }

  private resumeGame(): void {
    this.isPaused = false;
    if (!this.isGameOver) {
      this.startTimer();
    }
  }

  private exitGame(): void {
    this.clearTimer();
    this.getUIContext().getRouter().back();
  }

  // ── Display helpers ────────────────────────────────
  private timerText(): string {
    const secs = Math.ceil(this.timeRemainingMs / 1000);
    const m = Math.floor(secs / 60);
    const s = secs % 60;
    return `${m}:${s < 10 ? '0' + s : s}`;
  }

  private progressRatio(): number {
    return this.timeRemainingMs / GAME_DURATION_MS;
  }

  private progressColor(): string {
    if (this.timeRemainingMs > 20000) return SUCCESS;
    if (this.timeRemainingMs > 10000) return '#FFB020';
    return DANGER;
  }

  private calcScore(): number {
    return (this.level - 1) * 100;
  }

  // ── Build ──────────────────────────────────────────
  build() {
    Stack() {
      Column() {
        // ── Top bar ──────────────────────────────────
        Row() {
          Button({ type: ButtonType.Circle }) {
            Text('⏸').fontSize(18).fontColor(TEXT_MAIN)
          }
          .width(44).height(44).backgroundColor('rgba(255,255,255,0.82)')
          .onClick(() => this.pauseGame())

          Blank()

          Text('舒尔特表')
            .fontSize(19).fontColor(TEXT_MAIN).fontWeight(FontWeight.Bold)

          Blank()

          Row({ space: 4 }) {
            ForEach(
              [0, 1, 2] as number[],
              (i: number) => {
                Text(i < this.lives ? '❤' : '♡')
                  .fontSize(20)
                  .fontColor(i < this.lives ? DANGER : '#CBD5E1')
              },
              (i: number) => `life_${i}`
            )
          }
        }
        .width('100%').height(64)
        .padding({ left: 14, right: 14 })
        .alignItems(VerticalAlign.Center)

        // ── Level + timer row ─────────────────────────
        Row() {
          Text(`关卡 ${this.level}`)
            .fontSize(14).fontColor(TEXT_SUB).fontWeight(FontWeight.Bold)
            .padding({ left: 14, right: 14, top: 6, bottom: 6 })
            .backgroundColor('rgba(255,255,255,0.76)')
            .borderRadius(999)
          Blank()
          Text(`⏱ ${this.timerText()}`)
            .fontSize(14)
            .padding({ left: 14, right: 14, top: 6, bottom: 6 })
            .backgroundColor('rgba(255,255,255,0.76)')
            .borderRadius(999)
            .fontColor(this.timeRemainingMs <= 10000 ? DANGER : TEXT_SUB)
            .fontWeight(this.timeRemainingMs <= 10000 ? FontWeight.Bold : FontWeight.Normal)
        }
        .width('100%')
        .padding({ left: 16, right: 16, top: 2, bottom: 4 })

        // ── Progress bar ──────────────────────────────
        Stack({ alignContent: Alignment.Start }) {
          Row()
            .width('100%').height(6)
            .backgroundColor(BORDER_SOFT)
            .borderRadius(3)
          Row()
            .width(`${(this.progressRatio() * 100).toFixed(1)}%`)
            .height(6)
            .backgroundColor(this.progressColor())
            .borderRadius(3)
            .animation({ duration: 100, curve: Curve.Linear })
        }
        .width('100%')
        .padding({ left: 0, right: 0, top: 0, bottom: 4 })

        // ── Current number indicator ──────────────────
        Row() {
          Text('找到: ')
            .fontSize(18).fontColor(TEXT_SUB)
          Text(this.currentNumber <= TOTAL_NUMBERS ? this.currentNumber.toString() : '完成！')
            .fontSize(26).fontWeight(FontWeight.Bold)
            .fontColor(this.currentNumber <= TOTAL_NUMBERS ? THEME : SUCCESS)
        }
        .justifyContent(FlexAlign.Center)
        .width('100%')
        .padding({ top: 10, bottom: 8 })
        .backgroundColor(this.showWrongFeedback ? 'rgba(244,67,54,0.08)' : 'transparent')
        .animation({ duration: 200, curve: Curve.EaseOut })

        // ── Numbers grid ──────────────────────────────
        Grid() {
          ForEach(
            this.elements,
            (el: NumberElement) => {
              GridItem() {
                NumberCell({
                  num: el.num,
                  color: el.color,
                  isClicked: el.isClicked,
                  showWrongFeedback: this.showWrongFeedback && !el.isClicked,
                  onTap: (n: number) => this.onNumberTapped(n)
                })
              }
            },
            (el: NumberElement) => `${this.level}_${el.num}`
          )
        }
        .columnsTemplate('1fr 1fr 1fr 1fr 1fr 1fr')
        .columnsGap(4)
        .rowsGap(4)
        .width('100%')
        .layoutWeight(1)
        .padding(12)
        .margin({ left: 12, right: 12, top: 4, bottom: 12 })
        .backgroundColor(CARD_BG)
        .borderRadius(24)
        .shadow({ radius: 14, color: '#14000000', offsetX: 0, offsetY: 6 })
      }
      .width('100%').height('100%')
      .backgroundColor(PAGE_BG)

      // ── Pause dialog ──────────────────────────────────
      STPauseDialog({
        visible: this.isPaused,
        onResume: () => this.resumeGame(),
        onRestart: () => this.startGame(),
        onExit: () => this.exitGame()
      })

      // ── Game over overlay ─────────────────────────────
      STGameOverOverlay({
        visible: this.isGameOver,
        score: this.calcScore(),
        level: this.level,
        stars: calcStars(this.level),
        onPlayAgain: () => this.startGame(),
        onExit: () => this.exitGame()
      })
    }
    .width('100%').height('100%')
  }
}

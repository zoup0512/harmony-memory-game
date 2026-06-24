import { router } from '@kit.ArkUI';

const PAGE_BG = '#F3F8FF';
const CARD_BG = '#FFFFFF';
const TEXT_MAIN = '#1F2937';
const TEXT_SUB = '#6B7280';
const BORDER_SOFT = '#E5E7EB';
const THEME = '#26C6DA';
const DANGER = '#EF5B5B';
const SUCCESS = '#36B37E';

// ─── Progression (GameProgression1) ────────────────────────────────────────────

function hexGridSize(level: number): number {
  if (level <= 2) return 3;
  if (level <= 5) return 4;
  if (level <= 10) return 5;
  if (level <= 15) return 6;
  if (level <= 20) return 7;
  return 8;
}

function hexWinCells(level: number): number {
  let c: number;
  if (level === 1) c = 3;
  else if (level === 2) c = 4;
  else if (level === 3) c = 5;
  else if (level === 4) c = 6;
  else if (level === 5) c = 7;
  else if (level === 6) c = 8;
  else if (level === 7) c = 9;
  else if (level === 8) c = 10;
  else if (level === 9) c = 11;
  else if (level === 10) c = 12;
  else if (level <= 15) c = 13 + (level - 10);
  else if (level <= 20) c = 18 + (level - 15);
  else c = 23 + (level - 20);
  const gs = hexGridSize(level);
  return Math.min(c, gs * gs - 1);
}

function hexStars(level: number): number {
  if (level >= 15) return 3;
  if (level >= 10) return 2;
  if (level >= 5) return 1;
  return 0;
}

// ─── Helpers ───────────────────────────────────────────────────────────────────

function genChallenge(total: number, count: number): number[] {
  const all: number[] = [];
  for (let i = 0; i < total; i++) all.push(i);
  for (let i = all.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    const t = all[i]; all[i] = all[j]; all[j] = t;
  }
  return all.slice(0, count);
}

function hexCenterXY(row: number, col: number, radius: number, W: number, H: number, gs: number): number[] {
  const hs = radius * 1.73;
  const vs = radius * 1.5;
  const off = row % 2 === 1 ? hs / 2 : 0;
  const x = col * hs + off + radius * 2;
  const y = row * vs + radius * 2;
  const tw = (gs - 1) * hs + hs / 2;
  const th = (gs - 1) * vs;
  const cox = (W - tw) / 2;
  const coy = (H - th) / 2;
  return [x + cox, y + coy];
}

function touchToHex(tx: number, ty: number, W: number, H: number, gs: number): number {
  const radius = Math.min(W / (gs * 2.2), H / (gs * 2.2));
  for (let row = 0; row < gs; row++) {
    for (let col = 0; col < gs; col++) {
      const p = hexCenterXY(row, col, radius, W, H, gs);
      const dist = Math.sqrt((tx - p[0]) * (tx - p[0]) + (ty - p[1]) * (ty - p[1]));
      if (dist <= radius) return row * gs + col;
    }
  }
  return -1;
}

// ─── Pause dialog ──────────────────────────────────────────────────────────────

@Component
struct HexPause {
  @Prop visible: boolean = false;
  onResume: () => void = () => {};
  onRestart: () => void = () => {};
  onExit: () => void = () => {};
  build() {
    if (this.visible) {
      Stack() {
        Column().width('100%').height('100%').backgroundColor('rgba(15,23,42,0.45)')
        Column({ space: 14 }) {
          Text('游戏暂停').fontSize(22).fontWeight(FontWeight.Bold).fontColor(TEXT_MAIN)
          Text('蜂巢记忆').fontSize(14).fontColor(TEXT_SUB)
          Divider().color(BORDER_SOFT)
          Button({ type: ButtonType.Normal }) {
            Row({ space: 8 }) { Text('▶').fontColor('#FFF'); Text('继续游戏').fontColor('#FFF') }.justifyContent(FlexAlign.Center)
          }.width('100%').height(52).backgroundColor(SUCCESS).borderRadius(12).onClick(() => this.onResume())
          Button({ type: ButtonType.Normal }) {
            Row({ space: 8 }) { Text('↺').fontColor('#FFF'); Text('重新开始').fontColor('#FFF') }.justifyContent(FlexAlign.Center)
          }.width('100%').height(52).backgroundColor(THEME).borderRadius(12).onClick(() => this.onRestart())
          Button({ type: ButtonType.Normal }) {
            Row({ space: 8 }) { Text('✕').fontColor('#FFF'); Text('退出游戏').fontColor('#FFF') }.justifyContent(FlexAlign.Center)
          }.width('100%').height(52).backgroundColor(DANGER).borderRadius(12).onClick(() => this.onExit())
        }
        .padding(24).backgroundColor(CARD_BG).borderRadius(24)
        .shadow({ radius: 24, color: '#26000000', offsetX: 0, offsetY: 10 })
        .width('85%').alignItems(HorizontalAlign.Center)
      }.width('100%').height('100%').position({ x: 0, y: 0 }).zIndex(100)
    }
  }
}

// ─── Game over overlay ─────────────────────────────────────────────────────────

@Component
struct HexGameOver {
  @Prop visible: boolean = false;
  @Prop score: number = 0;
  @Prop level: number = 1;
  @Prop stars: number = 0;
  onPlayAgain: () => void = () => {};
  onExit: () => void = () => {};
  build() {
    if (this.visible) {
      Stack() {
        Column().width('100%').height('100%').backgroundColor('rgba(15,23,42,0.50)')
        Column({ space: 18 }) {
          Text('游戏结束').fontSize(30).fontWeight(FontWeight.Bold).fontColor(TEXT_MAIN)
          Row({ space: 6 }) {
            Text(this.stars >= 1 ? '★' : '☆').fontSize(38).fontColor(this.stars >= 1 ? '#FFC83D' : '#CBD5E1')
            Text(this.stars >= 2 ? '★' : '☆').fontSize(38).fontColor(this.stars >= 2 ? '#FFC83D' : '#CBD5E1')
            Text(this.stars >= 3 ? '★' : '☆').fontSize(38).fontColor(this.stars >= 3 ? '#FFC83D' : '#CBD5E1')
          }
          Column({ space: 8 }) {
            Row({ space: 6 }) {
              Text('最终得分：').fontSize(16).fontColor(TEXT_SUB)
              Text(this.score.toString()).fontSize(22).fontWeight(FontWeight.Bold).fontColor(THEME)
            }
            Row({ space: 6 }) {
              Text('到达关卡：').fontSize(16).fontColor(TEXT_SUB)
              Text(this.level.toString()).fontSize(22).fontWeight(FontWeight.Bold).fontColor(SUCCESS)
            }
          }
          Button({ type: ButtonType.Normal }) {
            Text('再玩一次').fontSize(18).fontWeight(FontWeight.Bold).fontColor('#FFFFFF')
          }.width('100%').height(56).backgroundColor(SUCCESS).borderRadius(12).onClick(() => this.onPlayAgain())
          Button({ type: ButtonType.Normal }) {
            Text('退出游戏').fontSize(16).fontColor('#FFFFFF')
          }.width('100%').height(48).backgroundColor(DANGER).borderRadius(12).onClick(() => this.onExit())
        }
        .padding(28).backgroundColor(CARD_BG).borderRadius(24)
        .shadow({ radius: 24, color: '#26000000', offsetX: 0, offsetY: 10 })
        .width('85%').alignItems(HorizontalAlign.Center)
      }.width('100%').height('100%').position({ x: 0, y: 0 }).zIndex(200)
    }
  }
}

// ─── Main page ─────────────────────────────────────────────────────────────────

@Entry
@Component
struct HexagonsPage {
  private settings: RenderingContextSettings = new RenderingContextSettings(true);
  private ctx: CanvasRenderingContext2D = new CanvasRenderingContext2D(this.settings);

  @State private level: number = 1;
  @State private score: number = 0;
  @State private lives: number = 3;
  @State private gridSize: number = 3;
  @State private challengeCells: number[] = [];
  @State private selectedCells: number[] = [];
  @State private gameState: string = 'IDLE';
  @State private isPaused: boolean = false;
  @State private isGameOver: boolean = false;

  private canvasReady: boolean = false;
  private flowId: number = 0;

  aboutToDisappear(): void {
    this.flowId = this.flowId + 1;
  }

  // ── Game flow ──────────────────────────────────────

  private startGame(): void {
    this.level = 1;
    this.score = 0;
    this.lives = 3;
    this.isGameOver = false;
    this.isPaused = false;
    this.startLevel();
  }

  private startLevel(): void {
    this.flowId = this.flowId + 1;
    const fid = this.flowId;
    const gs = hexGridSize(this.level);
    const wc = hexWinCells(this.level);
    this.gridSize = gs;
    this.challengeCells = genChallenge(gs * gs, wc);
    this.selectedCells = [];
    this.gameState = 'SHOW_CHALLENGE';
    this.drawCanvas();

    setTimeout(() => {
      if (fid !== this.flowId || this.isPaused) return;
      this.gameState = 'USER_INPUT';
      this.drawCanvas();
    }, 2000);
  }

  private onCellClick(idx: number): void {
    if (this.gameState !== 'USER_INPUT') return;

    if (this.selectedCells.includes(idx)) {
      this.selectedCells = this.selectedCells.filter((c: number) => c !== idx);
    } else {
      this.selectedCells = [...this.selectedCells, idx];
    }
    this.drawCanvas();

    if (this.selectedCells.length === this.challengeCells.length) {
      this.checkAnswer();
    }
  }

  private checkAnswer(): void {
    const isCorrect =
      this.challengeCells.every((c: number) => this.selectedCells.includes(c)) &&
      this.selectedCells.every((c: number) => this.challengeCells.includes(c));

    if (isCorrect) {
      this.score = this.score + 16 + (this.level * 2);
      this.gameState = 'SUCCESS';
      this.drawCanvas();
      this.flowId = this.flowId + 1;
      const fid = this.flowId;
      setTimeout(() => {
        if (fid !== this.flowId) return;
        this.level = this.level + 1;
        this.startLevel();
      }, 500);
    } else {
      this.gameState = 'FAILURE';
      this.drawCanvas();
      this.flowId = this.flowId + 1;
      const fid = this.flowId;
      setTimeout(() => {
        if (fid !== this.flowId) return;
        const nl = this.lives - 1;
        this.lives = nl;
        if (nl <= 0) {
          this.lives = 0;
          this.isGameOver = true;
          this.gameState = 'GAME_OVER';
          this.drawCanvas();
        } else {
          this.startLevel();
        }
      }, 1000);
    }
  }

  // ── Canvas drawing ─────────────────────────────────

  private drawCanvas(): void {
    if (!this.canvasReady) return;
    const W = this.ctx.width;
    const H = this.ctx.height;
    const gs = this.gridSize;
    const radius = Math.min(W / (gs * 2.2), H / (gs * 2.2));

    this.ctx.clearRect(0, 0, W, H);

    for (let row = 0; row < gs; row++) {
      for (let col = 0; col < gs; col++) {
        const idx = row * gs + col;
        const p = hexCenterXY(row, col, radius, W, H, gs);
        this.drawHex(p[0], p[1], radius * 0.92, this.cellColor(idx));
      }
    }
  }

  private drawHex(cx: number, cy: number, r: number, color: string): void {
    this.ctx.beginPath();
    for (let i = 0; i <= 6; i++) {
      const angle = Math.PI / 3 * i;
      const px = cx + r * Math.cos(angle);
      const py = cy + r * Math.sin(angle);
      if (i === 0) this.ctx.moveTo(px, py);
      else this.ctx.lineTo(px, py);
    }
    this.ctx.closePath();
    this.ctx.fillStyle = color;
    this.ctx.fill();
    this.ctx.strokeStyle = '#FFFFFF';
    this.ctx.lineWidth = 2.5;
    this.ctx.stroke();
  }

  private cellColor(idx: number): string {
    const isC = this.challengeCells.includes(idx);
    const isS = this.selectedCells.includes(idx);
    if (this.gameState === 'SHOW_CHALLENGE') return isC ? SUCCESS : '#CBD5E1';
    if (this.gameState === 'USER_INPUT') return isS ? THEME : '#E5E7EB';
    if (this.gameState === 'SUCCESS') return isC ? SUCCESS : '#E5E7EB';
    if (this.gameState === 'FAILURE') {
      if (isC && isS) return SUCCESS;
      if (isC && !isS) return '#FFC83D';
      if (!isC && isS) return DANGER;
      return '#E5E7EB';
    }
    return '#E5E7EB';
  }

  // ── UI helpers ─────────────────────────────────────

  private stateText(): string {
    if (this.gameState === 'SHOW_CHALLENGE') return `记住 ${this.challengeCells.length} 个高亮六边形`;
    if (this.gameState === 'USER_INPUT') {
      const rem = this.challengeCells.length - this.selectedCells.length;
      return rem > 0 ? `还需点击 ${rem} 个六边形` : '正在判断...';
    }
    if (this.gameState === 'SUCCESS') return '✓ 正确！';
    if (this.gameState === 'FAILURE') return '✗ 错误，已显示正确答案';
    return ' ';
  }

  private stateColor(): string {
    if (this.gameState === 'SUCCESS') return SUCCESS;
    if (this.gameState === 'FAILURE') return DANGER;
    if (this.gameState === 'SHOW_CHALLENGE') return '#FFB020';
    return THEME;
  }

  private tintBg(hex: string): string {
    const r = parseInt(hex.slice(1, 3), 16);
    const g = parseInt(hex.slice(3, 5), 16);
    const b = parseInt(hex.slice(5, 7), 16);
    return `rgba(${r},${g},${b},0.12)`;
  }

  // ── Build ──────────────────────────────────────────

  build() {
    Stack() {
      Column() {
        // Top bar
        Row() {
          Button({ type: ButtonType.Circle }) {
            Text('⏸').fontSize(18).fontColor(TEXT_MAIN)
          }
          .width(44).height(44).backgroundColor('rgba(255,255,255,0.82)')
          .onClick(() => {
            if (!this.isGameOver) {
              this.flowId = this.flowId + 1;
              this.isPaused = true;
            }
          })
          Blank()
          Text('蜂巢记忆').fontSize(19).fontWeight(FontWeight.Bold).fontColor(TEXT_MAIN)
          Blank()
          Row({ space: 4 }) {
            ForEach(
              [0, 1, 2] as number[],
              (i: number) => {
                Text(i < this.lives ? '❤' : '♡').fontSize(20)
                  .fontColor(i < this.lives ? DANGER : '#CBD5E1')
              },
              (i: number) => `hxl_${i}`
            )
          }
        }
        .width('100%').height(64).padding({ left: 14, right: 14 }).alignItems(VerticalAlign.Center)

        Row() {
          Text(`关卡 ${this.level}`).fontSize(14).fontColor(TEXT_SUB).fontWeight(FontWeight.Bold)
            .padding({ left: 14, right: 14, top: 6, bottom: 6 })
            .backgroundColor('rgba(255,255,255,0.76)')
            .borderRadius(999)
          Blank()
          Text(`得分 ${this.score}`).fontSize(14).fontColor(THEME).fontWeight(FontWeight.Bold)
            .padding({ left: 14, right: 14, top: 6, bottom: 6 })
            .backgroundColor('rgba(255,255,255,0.76)')
            .borderRadius(999)
        }
        .width('100%').padding({ left: 16, right: 16, bottom: 6 })

        // State indicator
        Text(this.stateText())
          .fontSize(18).fontColor(this.stateColor()).fontWeight(FontWeight.Bold)
          .textAlign(TextAlign.Center).width('100%')
          .padding({ top: 12, bottom: 12, left: 16, right: 16 })
          .backgroundColor(this.tintBg(this.stateColor()))
          .borderRadius(18)
          .shadow({ radius: 8, color: '#12000000', offsetX: 0, offsetY: 3 })
          .margin({ left: 16, right: 16, top: 4, bottom: 4 })

        // Canvas
        Canvas(this.ctx)
          .width('100%')
          .layoutWeight(1)
          .backgroundColor(CARD_BG)
          .borderRadius(24)
          .shadow({ radius: 14, color: '#14000000', offsetX: 0, offsetY: 6 })
          .margin({ left: 12, right: 12, top: 4, bottom: 8 })
          .onReady(() => {
            this.canvasReady = true;
            this.startGame();
          })
          .onTouch((event: TouchEvent) => {
            if (event.type !== TouchType.Down) return;
            if (this.gameState !== 'USER_INPUT') return;
            const tx = event.touches[0].x;
            const ty = event.touches[0].y;
            const idx = touchToHex(tx, ty, this.ctx.width, this.ctx.height, this.gridSize);
            if (idx !== -1) this.onCellClick(idx);
          })

        // Bottom hint
        Text(this.gameState === 'SHOW_CHALLENGE' ? '观察完毕后将隐藏' : '点击正确的六边形位置')
          .fontSize(12).fontColor(TEXT_SUB).textAlign(TextAlign.Center)
          .width('100%').padding({ top: 6, bottom: 12 })
      }
      .width('100%').height('100%').backgroundColor(PAGE_BG)

      HexPause({
        visible: this.isPaused,
        onResume: () => {
          this.isPaused = false;
          if (this.gameState === 'SHOW_CHALLENGE' || this.gameState === 'USER_INPUT') {
            this.startLevel();
          }
        },
        onRestart: () => { this.isPaused = false; this.startGame(); },
        onExit: () => { this.flowId = this.flowId + 1; router.back(); }
      })

      HexGameOver({
        visible: this.isGameOver,
        score: this.score,
        level: this.level,
        stars: hexStars(this.level),
        onPlayAgain: () => this.startGame(),
        onExit: () => { this.flowId = this.flowId + 1; router.back(); }
      })
    }
    .width('100%').height('100%')
  }
}

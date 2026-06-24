export {}

// ─── Data types ────────────────────────────────────────────────────────────────

interface GameEntry {
  id: number;
  name: string;
  description: string;
  category: string;
  icon: string;
  ability: string;
  difficulty: string;
}

interface CategoryEntry {
  id: string;
  name: string;
  color: string;
  icon: string;
}

// ─── Game categories ───────────────────────────────────────────────────────────

const CAT_ALL = 'ALL';
const CAT_MEMORY = 'MEMORY';
const CAT_ATTENTION = 'ATTENTION';
const CAT_SPEED = 'SPEED';
const CAT_PROBLEM = 'PROBLEM_SOLVING';
const CAT_FLEXIBILITY = 'FLEXIBILITY';
const CAT_IMAGINATION = 'IMAGINATION';

const UI_BG = '#F3F8FF';
const UI_CARD = '#FFFFFF';
const UI_TEXT = '#1F2937';
const UI_SUB = '#6B7280';
const UI_BORDER = '#E5E7EB';
const UI_PRIMARY = '#5B7CFA';
const UI_SUCCESS = '#36B37E';
const UI_DANGER = '#EF5B5B';
const UI_STAR = '#FFC83D';

const CATEGORIES: CategoryEntry[] = [
  { id: CAT_ALL,        name: '全部',   color: UI_SUB,      icon: '◈' },
  { id: CAT_MEMORY,     name: '记忆',   color: '#5B8DEF',   icon: '◉' },
  { id: CAT_ATTENTION,  name: '注意力', color: '#00A896',   icon: '◎' },
  { id: CAT_SPEED,      name: '速度',   color: UI_DANGER,   icon: '◆' },
  { id: CAT_PROBLEM,    name: '问题解决', color: '#7E57C2', icon: '◧' },
  { id: CAT_FLEXIBILITY,name: '灵活性', color: UI_STAR,     icon: '⇄' },
  { id: CAT_IMAGINATION,name: '想象力', color: '#26C6DA',   icon: '◈' },
];

function categoryColor(cat: string): string {
  switch (cat) {
    case CAT_MEMORY:      return '#5B8DEF';
    case CAT_ATTENTION:   return '#00A896';
    case CAT_SPEED:       return UI_DANGER;
    case CAT_PROBLEM:     return '#7E57C2';
    case CAT_FLEXIBILITY: return UI_STAR;
    case CAT_IMAGINATION: return '#26C6DA';
    default:              return UI_SUB;
  }
}

function categoryIcon(cat: string): string {
  switch (cat) {
    case CAT_MEMORY:      return '🧠';
    case CAT_ATTENTION:   return '👁';
    case CAT_SPEED:       return '⚡';
    case CAT_PROBLEM:     return '🧩';
    case CAT_FLEXIBILITY: return '↔';
    case CAT_IMAGINATION: return '💡';
    default:              return '◈';
  }
}

function categoryName(cat: string): string {
  switch (cat) {
    case CAT_MEMORY:      return '记忆';
    case CAT_ATTENTION:   return '注意力';
    case CAT_SPEED:       return '速度';
    case CAT_PROBLEM:     return '问题解决';
    case CAT_FLEXIBILITY: return '灵活性';
    case CAT_IMAGINATION: return '想象力';
    default:              return '';
  }
}

// ─── Game data ─────────────────────────────────────────────────────────────────

const ALL_GAMES: GameEntry[] = [
  // Memory (记忆)
  { id: 1,  name: '记忆网格',   description: '记住亮起的位置，按顺序找回', category: CAT_MEMORY, icon: '🧠', ability: '空间记忆', difficulty: '入门' },
  { id: 2,  name: '蜂巢记忆',   description: '在六边形蜂巢里完成空间挑战', category: CAT_MEMORY, icon: '⬡', ability: '空间记忆', difficulty: '挑战' },
  { id: 3,  name: '谁是新的',   description: '识别新出现的元素',       category: CAT_MEMORY, icon: '✨', ability: '视觉记忆', difficulty: '即将推出' },
  { id: 5,  name: '跟随路径',   description: '记住并跟随路径',         category: CAT_MEMORY, icon: '〰', ability: '路径记忆', difficulty: '即将推出' },
  { id: 6,  name: '图像漩涡',   description: '图像记忆游戏',           category: CAT_MEMORY, icon: '🌀', ability: '图像记忆', difficulty: '即将推出' },
  { id: 8,  name: '找图片',     description: '找到之前看过的图片',     category: CAT_MEMORY, icon: '🖼', ability: '图像识别', difficulty: '即将推出' },
  // Attention (注意力)
  { id: 4,  name: '旋转网格',   description: '记住图案并判断旋转后的位置', category: CAT_ATTENTION, icon: '🔄', ability: '空间想象', difficulty: '进阶' },
  { id: 7,  name: '抓住它们',   description: '快速抓住目标',           category: CAT_ATTENTION, icon: '🎯', ability: '反应注意', difficulty: '即将推出' },
  // Speed (速度)
  { id: 24, name: '唯一的',     description: '找出唯一不同的元素',     category: CAT_SPEED, icon: '◆', ability: '视觉速度', difficulty: '即将推出' },
  { id: 12, name: '全部相同',   description: '判断所有元素是否相同',   category: CAT_SPEED, icon: '≡', ability: '快速判断', difficulty: '即将推出' },
  { id: 13, name: '排序数字',   description: '快速排序数字',           category: CAT_SPEED, icon: '🔢', ability: '排序速度', difficulty: '即将推出' },
  { id: 18, name: '找到全部',   description: '找到所有目标',           category: CAT_SPEED, icon: '🔎', ability: '搜索速度', difficulty: '即将推出' },
  { id: 22, name: '舒尔特表',   description: '按顺序快速找到数字',     category: CAT_SPEED, icon: '🔢', ability: '专注搜索', difficulty: '速度' },
  // Problem Solving (问题解决)
  { id: 25, name: '正确吗',     description: '判断数学等式是否正确',   category: CAT_PROBLEM, icon: '✓', ability: '逻辑判断', difficulty: '即将推出' },
  { id: 11, name: '更多更少',   description: '比较数字大小',           category: CAT_PROBLEM, icon: '↕', ability: '数量比较', difficulty: '即将推出' },
  { id: 16, name: '248',        description: '连接同色方块，移动行列创造高分', category: CAT_PROBLEM, icon: '🎨', ability: '策略连接', difficulty: '限时' },
  { id: 20, name: '对称',       description: '判断对称性',             category: CAT_PROBLEM, icon: '◇', ability: '图形逻辑', difficulty: '即将推出' },
  { id: 21, name: '激光',       description: '激光反射游戏',           category: CAT_PROBLEM, icon: '🔦', ability: '路径推理', difficulty: '即将推出' },
  // Flexibility (灵活性)
  { id: 26, name: '颜色',       description: '判断颜色词与文字颜色是否匹配', category: CAT_FLEXIBILITY, icon: '🌈', ability: '认知灵活', difficulty: '即将推出' },
  { id: 14, name: '纸飞机',     description: '纸飞机方向游戏',         category: CAT_FLEXIBILITY, icon: '✈', ability: '方向切换', difficulty: '即将推出' },
  { id: 15, name: '像之前的吗', description: '判断是否与之前相同',     category: CAT_FLEXIBILITY, icon: '↩', ability: '工作记忆', difficulty: '即将推出' },
  // Imagination (想象力)
  { id: 19, name: '数一数',     description: '数数游戏',               category: CAT_IMAGINATION, icon: '🧮', ability: '数量想象', difficulty: '即将推出' },
  { id: 17, name: '金字塔',     description: '金字塔游戏',             category: CAT_IMAGINATION, icon: '🔺', ability: '空间结构', difficulty: '即将推出' },
];

function filteredGames(selectedCat: string): GameEntry[] {
  if (selectedCat === CAT_ALL) return ALL_GAMES;
  const result: GameEntry[] = [];
  for (let i = 0; i < ALL_GAMES.length; i++) {
    if (ALL_GAMES[i].category === selectedCat) {
      result.push(ALL_GAMES[i]);
    }
  }
  return result;
}

// ─── Category chip ─────────────────────────────────────────────────────────────

@Component
struct CategoryChip {
  @Prop label: string = '';
  @Prop color: string = '#757575';
  @Prop selected: boolean = false;
  onSelect: () => void = () => {};

  build() {
    Text(this.label)
      .fontSize(13)
      .fontColor(this.selected ? '#FFFFFF' : this.color)
      .fontWeight(FontWeight.Bold)
      .padding({ left: 16, right: 16, top: 8, bottom: 8 })
      .backgroundColor(this.selected ? this.color : UI_CARD)
      .borderRadius(999)
      .border({ width: 1, color: this.selected ? this.color : UI_BORDER })
      .shadow({ radius: this.selected ? 8 : 2, color: this.selected ? '#22000000' : '#0A000000', offsetX: 0, offsetY: 2 })
      .onClick(() => this.onSelect())
  }
}

// ─── Game card ─────────────────────────────────────────────────────────────────

@Component
struct GameCard {
  @Prop gameId: number = 0;
  @Prop gameName: string = '';
  @Prop gameDesc: string = '';
  @Prop category: string = '';
  @Prop icon: string = '';
  @Prop ability: string = '';
  @Prop difficulty: string = '';
  onPlay: () => void = () => {};

  private isAvailable(): boolean {
    return this.gameId === 1 || this.gameId === 4 || this.gameId === 22 || this.gameId === 16 || this.gameId === 2;
  }

  build() {
    Stack() {
      Row({ space: 14 }) {
        Stack() {
          Column()
            .width(64)
            .height(64)
            .backgroundColor(categoryColor(this.category))
            .borderRadius(20)
            .opacity(this.isAvailable() ? 1.0 : 0.35)
          Text(this.icon)
            .fontSize(30)
        }
        .width(64)
        .height(64)

        Column({ space: 8 }) {
          Row() {
            Text(this.gameName)
              .fontSize(18)
              .fontWeight(FontWeight.Bold)
              .fontColor(this.isAvailable() ? UI_TEXT : '#9CA3AF')
              .layoutWeight(1)
              .maxLines(1)
            Text(this.isAvailable() ? '开始' : '锁定')
              .fontSize(12)
              .fontColor(this.isAvailable() ? '#FFFFFF' : '#9CA3AF')
              .fontWeight(FontWeight.Bold)
              .padding({ left: 12, right: 12, top: 5, bottom: 5 })
              .backgroundColor(this.isAvailable() ? categoryColor(this.category) : '#F3F4F6')
              .borderRadius(999)
          }
          .width('100%')
          .alignItems(VerticalAlign.Center)

          Text(this.gameDesc)
            .fontSize(13)
            .fontColor(UI_SUB)
            .lineHeight(18)
            .maxLines(2)

          Row({ space: 8 }) {
            Text(this.ability)
              .fontSize(11)
              .fontColor(categoryColor(this.category))
              .fontWeight(FontWeight.Bold)
              .padding({ left: 10, right: 10, top: 4, bottom: 4 })
              .backgroundColor('#F8FAFC')
              .borderRadius(999)
            Text(this.difficulty)
              .fontSize(11)
              .fontColor(UI_SUB)
              .fontWeight(FontWeight.Medium)
              .padding({ left: 10, right: 10, top: 4, bottom: 4 })
              .backgroundColor('#F8FAFC')
              .borderRadius(999)
          }
        }
        .layoutWeight(1)
        .alignItems(HorizontalAlign.Start)
      }
      .width('100%')
      .padding(16)
    }
    .width('100%')
    .backgroundColor(UI_CARD)
    .borderRadius(24)
    .shadow({ radius: 14, color: '#14000000', offsetX: 0, offsetY: 6 })
    .opacity(this.isAvailable() ? 1.0 : 0.72)
    .onClick(() => {
      if (this.isAvailable()) {
        this.onPlay();
      }
    })
  }
}

// ─── Sprint tab ────────────────────────────────────────────────────────────────

@Component
struct SprintTab {
  @State private selectedCat: string = CAT_ALL;
  private catScroller: Scroller = new Scroller();
  private listScroller: Scroller = new Scroller();

  build() {
    Column() {
      Column({ space: 12 }) {
        Stack() {
          Column()
            .width('100%')
            .height(150)
            .backgroundColor(UI_PRIMARY)
            .borderRadius(28)
          Row() {
            Column({ space: 8 }) {
              Text('今天训练了吗？')
                .fontSize(24)
                .fontWeight(FontWeight.Bold)
                .fontColor('#FFFFFF')
              Text('选择一个小游戏，开始 3 分钟专注挑战')
                .fontSize(14)
                .fontColor('#EAF0FF')
                .lineHeight(20)
                .maxLines(2)
              Row({ space: 8 }) {
                Text('5 个可玩')
                  .fontSize(12)
                  .fontColor(UI_PRIMARY)
                  .fontWeight(FontWeight.Bold)
                  .padding({ left: 10, right: 10, top: 5, bottom: 5 })
                  .backgroundColor('#FFFFFF')
                  .borderRadius(999)
                Text('快速训练')
                  .fontSize(12)
                  .fontColor('#FFFFFF')
                  .fontWeight(FontWeight.Bold)
                  .padding({ left: 10, right: 10, top: 5, bottom: 5 })
                  .backgroundColor('rgba(255,255,255,0.22)')
                  .borderRadius(999)
              }
            }
            .layoutWeight(1)
            .alignItems(HorizontalAlign.Start)
            Text('⚡')
              .fontSize(54)
          }
          .padding(20)
          .width('100%')
          .height(150)
          .alignItems(VerticalAlign.Center)
        }
        .width('100%')
        .shadow({ radius: 16, color: '#245B7CFA', offsetX: 0, offsetY: 8 })
      }
      .width('100%')
      .padding({ left: 16, right: 16, top: 16, bottom: 12 })

      Scroll(this.catScroller) {
        Row({ space: 8 }) {
          ForEach(
            CATEGORIES,
            (cat: CategoryEntry) => {
              CategoryChip({
                label: cat.name,
                color: cat.color,
                selected: this.selectedCat === cat.id,
                onSelect: () => {
                  this.selectedCat = cat.id;
                }
              })
            },
            (cat: CategoryEntry) => cat.id
          )
        }
        .padding({ left: 16, right: 16, top: 2, bottom: 4 })
      }
      .scrollable(ScrollDirection.Horizontal)
      .scrollBar(BarState.Off)
      .width('100%')

      List({ space: 14, scroller: this.listScroller }) {
        ForEach(
          filteredGames(this.selectedCat),
          (game: GameEntry) => {
            ListItem() {
              GameCard({
                gameId: game.id,
                gameName: game.name,
                gameDesc: game.description,
                category: game.category,
                icon: game.icon,
                ability: game.ability,
                difficulty: game.difficulty,
                onPlay: () => {
                  const r = this.getUIContext().getRouter();
                  if (game.id === 4) {
                    r.pushUrl({ url: 'pages/RotatingGridPage' });
                  } else if (game.id === 22) {
                    r.pushUrl({ url: 'pages/SchultzTablesPage' });
                  } else if (game.id === 16) {
                    r.pushUrl({ url: 'pages/Game248Page' });
                  } else if (game.id === 2) {
                    r.pushUrl({ url: 'pages/HexagonsPage' });
                  } else {
                    r.pushUrl({ url: 'pages/MemoryGridPage' });
                  }
                }
              })
              .padding({ left: 16, right: 16 })
            }
          },
          (game: GameEntry) => game.id.toString()
        )
        ListItem().height(20)
      }
      .layoutWeight(1)
      .width('100%')
      .divider({ strokeWidth: 0 })
      .margin({ top: 12 })
    }
    .width('100%')
    .height('100%')
    .backgroundColor(UI_BG)
  }
}

// ─── Placeholder tabs ──────────────────────────────────────────────────────────

@Component
struct PlaceholderTab {
  @Prop title: string = '';
  @Prop icon: string = '';

  build() {
    Column({ space: 16 }) {
      Text(this.icon)
        .fontSize(56)
      Text(this.title)
        .fontSize(22)
        .fontWeight(FontWeight.Bold)
        .fontColor(UI_TEXT)
      Text('功能开发中')
        .fontSize(14)
        .fontColor(UI_SUB)
    }
    .width('100%')
    .height('100%')
    .justifyContent(FlexAlign.Center)
    .alignItems(HorizontalAlign.Center)
    .backgroundColor(UI_BG)
  }
}

// ─── Main page ─────────────────────────────────────────────────────────────────

@Entry
@Component
struct MainPage {
  @State private selectedTab: number = 0;
  @State private stars: number = 0;
  @State private coins: number = 0;

  // Tab definitions
  private tabIcons: string[] = ['▶', '🏋', '🏆', '👥', '☰'];
  private tabLabels: string[] = ['Sprint', 'Workout', 'Challenge', 'Online', '菜单'];

  build() {
    Column() {
      // ── Top bar ───────────────────────────────────────
      Row() {
        Column({ space: 2 }) {
          Text('Memory Games')
            .fontSize(20)
            .fontWeight(FontWeight.Bold)
            .fontColor(UI_TEXT)
          Text('脑力训练大厅')
            .fontSize(12)
            .fontColor(UI_SUB)
        }
        .alignItems(HorizontalAlign.Start)
        .layoutWeight(1)

        Row({ space: 4 }) {
          Text('★')
            .fontSize(18)
            .fontColor(UI_STAR)
          Text(this.stars.toString())
            .fontSize(15)
            .fontColor(UI_TEXT)
            .fontWeight(FontWeight.Bold)
        }
        .height(32)
        .padding({ left: 12, right: 12 })
        .backgroundColor('#FFF8E1')
        .borderRadius(999)
        .margin({ right: 8 })

        Row({ space: 4 }) {
          Text('💰')
            .fontSize(16)
          Text(this.coins.toString())
            .fontSize(15)
            .fontColor(UI_TEXT)
            .fontWeight(FontWeight.Bold)
        }
        .height(32)
        .padding({ left: 12, right: 12 })
        .backgroundColor('#ECFDF5')
        .borderRadius(999)
      }
      .width('100%')
      .height(64)
      .padding({ left: 16, right: 16 })
      .backgroundColor(UI_CARD)
      .shadow({ radius: 8, color: '#10000000', offsetX: 0, offsetY: 3 })
      .alignItems(VerticalAlign.Center)

      // ── Tab content ───────────────────────────────────
      Stack() {
        if (this.selectedTab === 0) {
          SprintTab()
        } else if (this.selectedTab === 1) {
          PlaceholderTab({ title: 'Workout', icon: '🏋' })
        } else if (this.selectedTab === 2) {
          PlaceholderTab({ title: 'Challenge', icon: '🏆' })
        } else if (this.selectedTab === 3) {
          PlaceholderTab({ title: 'Online', icon: '👥' })
        } else {
          PlaceholderTab({ title: '菜单', icon: '☰' })
        }
      }
      .layoutWeight(1)
      .width('100%')
      .backgroundColor(UI_BG)

      // ── Bottom navigation bar ─────────────────────────
      Row() {
        ForEach(
          [0, 1, 2, 3, 4] as number[],
          (idx: number) => {
            Column({ space: 3 }) {
              Text(this.tabIcons[idx])
                .fontSize(this.selectedTab === idx ? 22 : 20)
                .fontColor(this.selectedTab === idx ? UI_PRIMARY : '#9CA3AF')

              Text(this.tabLabels[idx])
                .fontSize(11)
                .fontColor(this.selectedTab === idx ? UI_PRIMARY : '#9CA3AF')
                .fontWeight(this.selectedTab === idx ? FontWeight.Bold : FontWeight.Normal)
            }
            .layoutWeight(1)
            .height(50)
            .justifyContent(FlexAlign.Center)
            .alignItems(HorizontalAlign.Center)
            .backgroundColor(this.selectedTab === idx ? '#EEF2FF' : 'transparent')
            .borderRadius(18)
            .margin({ left: 4, right: 4 })
            .onClick(() => {
              this.selectedTab = idx;
            })
          },
          (idx: number) => `tab_${idx}`
        )
      }
      .width('100%')
      .height(68)
      .padding({ left: 8, right: 8, top: 6, bottom: 8 })
      .backgroundColor(UI_CARD)
      .shadow({ radius: 12, color: '#14000000', offsetX: 0, offsetY: -4 })
    }
    .width('100%')
    .height('100%')
    .backgroundColor(UI_BG)
  }
}

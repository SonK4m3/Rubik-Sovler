package com.example.rubiksovler.ui.view.home

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rubiksovler.R
import com.example.rubiksovler.data.*
import com.example.rubiksovler.data.model.*
import com.example.rubiksovler.ui.theme.RubikSolverTheme
import com.example.rubiksovler.viewmodel.HomeViewmodel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var homeViewModel: HomeViewmodel? = null
    private lateinit var rubikController: RubikController

    override fun onCreate(savedInstanceState: Bundle?) {

        homeViewModel = HomeViewmodel()
        rubikController = RubikController(homeViewModel!!.cube.value)
        rubikController.randomMove()
        super.onCreate(savedInstanceState)
        setContent {
            RubikSolverTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Rubik2D(homeViewModel!!, rubikController)
                }
            }
        }
    }
}

//@Composable
//fun Rect3D(
//    modifier: Modifier = Modifier, offset: Offset
//) {
//    val positionA = listOf(
//        Offset(0F + offset.x, 0F), Offset(400F, 200F), Offset(400F, 600F), Offset(0F, 400F)
//    )
//
//    val positionB = listOf(
//        Offset(0F, 0F), Offset(400F, -200F), Offset(800F, 0F), Offset(400F, 200F)
//    )
//
//    val positionC = listOf(
//        Offset(400F, 200F), Offset(800F, 0F), Offset(800F, 400F), Offset(400F, 600F)
//    )
//
//    Canvas(modifier = modifier.size(400.dp)) {
//        val startX = 100F
//        val startY = 400F
//        val position2 = positionA.map { pos -> pos.plus(Offset(startX, startY)) }
//        val position3 = positionB.map { pos -> pos.plus(Offset(startX, startY)) }
//        val position4 = positionC.map { pos -> pos.plus(Offset(startX, startY)) }
//
//        val pathA = Path().apply {
//            moveTo(position2[0].x, position2[0].y)
//            lineTo(position2[1].x, position2[1].y)
//            lineTo(position2[2].x, position2[2].y)
//            lineTo(position2[3].x, position2[3].y)
//            close()
//        }
//
//        drawPath(
//            path = pathA, color = Color.Blue, style = Fill
//        )
//
//        val pathB = Path().apply {
//            moveTo(position3[0].x, position3[0].y)
//            lineTo(position3[1].x, position3[1].y)
//            lineTo(position3[2].x, position3[2].y)
//            lineTo(position3[3].x, position3[3].y)
//            close()
//        }
//
//        drawPath(
//            path = pathB, color = Color.Yellow, style = Fill
//        )
//
//        val pathC = Path().apply {
//            moveTo(position4[0].x, position4[0].y)
//            lineTo(position4[1].x, position4[1].y)
//            lineTo(position4[2].x, position4[2].y)
//            lineTo(position4[3].x, position4[3].y)
//            close()
//        }
//
//        drawPath(
//            path = pathC, color = Color.Red, style = Fill
//        )
//    }
//}

//@Composable
//fun Container() {
//    var rectanglePosition by remember { mutableStateOf(Offset.Zero) }
//
//    var isDragging by remember { mutableStateOf(false) }
//
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .pointerInput(Unit) {
//            detectDragGestures { change, dragAmount ->
//                isDragging = change.isConsumed
//                rectanglePosition += dragAmount
//                Log.d("CanvasExample", "rectanglePosition: $rectanglePosition")
//            }
//        }) {
//        Rect3D(offset = rectanglePosition)
//    }
//}

//@Composable
//fun CubeAnimation() {
//    var rotationState by remember { mutableStateOf(0f) }
//    val rotationAnimation = rememberInfiniteTransition()
//    val rotationValue by rotationAnimation.animateFloat(
//        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
//            animation = tween(5000, easing = LinearEasing), repeatMode = RepeatMode.Restart
//        )
//    )
//    rotationState = rotationValue
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            val canvasSize = size.minDimension
//            val cubeSize = canvasSize / 2
//            val cubeCenter = Offset(canvasSize / 2, canvasSize / 2)
//            val cubeColor = Color.Blue
//
//            rotate(rotationState, pivot = cubeCenter) {
//                drawCube(cubeCenter, cubeSize, cubeColor)
//            }
//        }
//    }
//}

//fun DrawScope.drawCube(center: Offset, size: Float, color: Color) {
//    val halfSize = size / 2
//
//    // Front face
//    drawRect(Color.White, topLeft = center - Offset(halfSize, halfSize), size = Size(size, size))
//
//    // Left face
//    drawRect(Color.Red, topLeft = center - Offset(halfSize, halfSize), size = Size(size, size))
//    drawLine(color, center - Offset(halfSize, halfSize), center - Offset(halfSize, -halfSize))
//    drawLine(color, center - Offset(halfSize, -halfSize), center + Offset(-halfSize, -halfSize))
//
//    // Right face
//    drawRect(Color.Yellow, topLeft = center - Offset(halfSize, halfSize), size = Size(size, size))
//    drawLine(color, center + Offset(halfSize, -halfSize), center + Offset(halfSize, halfSize))
//    drawLine(color, center - Offset(halfSize, -halfSize), center + Offset(halfSize, -halfSize))
//
//    // Top face
//    drawRect(Color.Green, topLeft = center - Offset(halfSize, -halfSize), size = Size(size, size))
//    drawLine(color, center - Offset(halfSize, -halfSize), center + Offset(halfSize, -halfSize))
//    drawLine(color, center + Offset(-halfSize, -halfSize), center + Offset(-halfSize, halfSize))
//
//    // Bottom face
////    drawRect(color, topLeft = center - Offset(halfSize, -halfSize), size = Size(size, size))
////    drawLine(color, center + Offset(-halfSize, halfSize), center + Offset(halfSize, halfSize))
////    drawLine(color, center + Offset(-halfSize, halfSize), center + Offset(-halfSize, -halfSize))
//}

@Composable
fun Rubik2D(homeViewModel: HomeViewmodel, rubikController: RubikController) {
    val mContext = LocalContext.current
    val matrix = homeViewModel.cube.value
    val undoImage = painterResource(id = R.drawable.baseline_undo_24)
    val redoImage = painterResource(id = R.drawable.baseline_redo_24)
    var step: ArrayList<RubikMove> by rememberSaveable { mutableStateOf(arrayListOf()) }

    val coroutineScope = rememberCoroutineScope()

    var isDragging by remember {
        mutableStateOf(false)
    }

    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    var matrixUpdateCounter by remember { mutableStateOf(0) }
    var canSolve by remember {
        mutableStateOf(false)
    }
    // Update matrix when history length changes
    LaunchedEffect(rubikController.getHistoryLength()) {
        matrixUpdateCounter++
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "position (x, y) = (${offset.x}, ${offset.y})",
            modifier = Modifier.safeContentPadding()
        )

        Button(onClick = {
            canSolve = rubikController.checkTotalColors()
            if (!canSolve) mToast(mContext, "Không tồn tại trạng thái")
            else {
                coroutineScope.launch {
                    step.clear()
                    step.addAll(rubikController.solve() as ArrayList<RubikMove>)
                    matrixUpdateCounter = 0
                }

                mToast(mContext, "solve")
            }
        },
            modifier = Modifier
                .safeContentPadding()
                .clip(
                    RoundedCornerShape(10.dp)
                ),
            content = {
                Text(text = "Solve")
            })

        if (canSolve) StepList(
            modifier = Modifier.fillMaxWidth(),
            steps = step,
            changeStep = {
                rubikController.move(it)
                matrixUpdateCounter++
            },
            undo = {
                rubikController.undo()
                matrixUpdateCounter--
            }
        )

        Text(
            text = "${rubikController.getCurrentMove()}",
            modifier = Modifier.safeContentPadding(),
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .pointerInput(Unit) {
                    detectDragGestures(onDragStart = { offset_ ->
                        offset = offset_
                        isDragging = true
                    }, onDrag = { _, offset_ ->
                        if (isDragging) {
                            offset = offset_
                            isDragging = false
                        }
                    }, onDragEnd = {
                        if (!isDragging) {
                            if (offset.x >= 7f && offset.x > offset.y)
                                rubikController.move(RubikMove.Y_)
                            else if (offset.x <= -7f && offset.x < offset.y)
                                rubikController.move(RubikMove.Y)
                            else if (offset.y >= 7f && offset.y > offset.x)
                                rubikController.move(RubikMove.X_)
                            else if (offset.y <= -7f && offset.y < offset.x)
                                rubikController.move(RubikMove.X)
                        }
                        offset = Offset.Zero
                    })
                }, contentAlignment = Alignment.Center
        ) {
            RubikMatrix(
                matrix = matrix.getF(),
                level = matrix.getLevel(),
                changeColor = { pos, c -> rubikController.changeColor(pos, c) },
                matrixUpdateCounter = matrixUpdateCounter
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                rubikController.undo()
                matrixUpdateCounter++
            }) {
                Icon(painter = undoImage, contentDescription = "undo")
            }

            Text(text = "${rubikController.getHistoryLength()}", modifier = Modifier.width(40.dp))

            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = redoImage, contentDescription = "redo")
            }

        }
    }
}

@Composable
fun RubikMatrix(
    matrix: ArrayList<RubikFaceColor>?,
    level: Int,
    matrixUpdateCounter: Int,
    changeColor: (pos: Int, c: RubikFaceColor) -> Unit
) {
    val matrixState = remember(matrix, matrixUpdateCounter) { matrix to matrixUpdateCounter }

    val selectedColor = remember { mutableStateOf(RubikFaceColor.Unspecified) }
    val pos = remember { mutableStateOf(-1) }

    Column {
        Text("${selectedColor.value}  ${pos.value}")
        LazyVerticalGrid(
            columns = GridCells.Fixed(level),
            modifier = Modifier
                .safeContentPadding()
                .background(Color.Black),
            contentPadding = PaddingValues(3.dp),
            userScrollEnabled = false
        ) {
            itemsIndexed(matrixState.first ?: emptyList()) { index, item ->
                Box(modifier = Modifier
                    .aspectRatio(1f)
                    .padding(3.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(item.rgb))
                    .clickable {
                        selectedColor.value = item
                        pos.value = index
                    })

                if (item == selectedColor.value && index == pos.value) {
                    ColorPickerDialog(onColorSelected = {
                        changeColor(pos.value, it)
                        selectedColor.value = RubikFaceColor.Unspecified
                    }, onDismissRequest = {
                        pos.value = -1
                        selectedColor.value = RubikFaceColor.Unspecified
                    })
                }
            }
        }
    }
}

@Composable
fun ColorPickerDialog(
    onColorSelected: (RubikFaceColor) -> Unit, onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismissRequest,
        title = { Text(text = "Select a color") },
        confirmButton = {},
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), modifier = Modifier.padding(16.dp)
            ) {
                items(Cube.orderColorDefault) { color ->
                    Box(modifier = Modifier
                        .size(50.dp)
                        .padding(3.dp)
                        .background(Color(color.rgb))
                        .clickable { onColorSelected(color) })
                }
            }
        })
}

@Composable
fun StepList(
    modifier: Modifier,
    steps: List<RubikMove>,
    changeStep: (RubikMove) -> Unit,
    undo: (RubikMove) -> Unit
) {
    val currentStep = remember {
        mutableStateOf(-1)
    }

    val stepModifier = Modifier
        .size(48.dp)
        .padding(3.dp)
        .background(Color.Gray)
        .clip(RoundedCornerShape(10.dp))

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.safeContentPadding()) {
            Button(
                onClick = {
                    if (currentStep.value > -1) {
                        undo(steps[currentStep.value])
                        currentStep.value -= 1
                    }
                },
                modifier = Modifier.safeContentPadding()
            ) {
                Text(text = "Pre")
            }
            if (currentStep.value > 1)
                StepItem(modifier = stepModifier, steps[currentStep.value - 2])
            if (currentStep.value > 0)
                StepItem(modifier = stepModifier, steps[currentStep.value - 1])
            if (currentStep.value > -1 && currentStep.value < steps.size)
                StepItem(modifier = stepModifier, steps[currentStep.value], center = true)
            if (currentStep.value < steps.size - 1)
                StepItem(modifier = stepModifier, move = steps[currentStep.value + 1])
            if (currentStep.value < steps.size - 2 && currentStep.value < 2)
                StepItem(modifier = stepModifier, move = steps[currentStep.value + 2])
            Button(
                onClick = {
                    if (currentStep.value < steps.size - 1) {
                        currentStep.value += 1
                        changeStep(steps[currentStep.value])
                    }
                },
                modifier = Modifier.safeContentPadding()
            ) {
                Text(text = "Next")
            }
        }
    }

}

@Composable
fun StepItem(modifier: Modifier, move: RubikMove, center: Boolean = false) {
    Box(
        modifier = if (!center) modifier else modifier
            .border(
                BorderStroke(2.dp, SolidColor(Color.Yellow))
            )
            .size(52.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$move")
    }
}

private fun mToast(context: Context, content: String) {
    Toast.makeText(context, content, Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val homeViewModel = HomeViewmodel()
    val rubikController = RubikController(homeViewModel.cube.value)
    rubikController.randomMove()

    RubikSolverTheme {
        Rubik2D(homeViewModel, rubikController)
    }
}

@Preview(showBackground = true)
@Composable
fun SolveListPreview() {
    val step =
        listOf(RubikMove.F, RubikMove.R, RubikMove.U, RubikMove.R_, RubikMove.U_, RubikMove.F_)

    StepList(modifier = Modifier.fillMaxWidth(), steps = step, changeStep = {}, undo={})
}
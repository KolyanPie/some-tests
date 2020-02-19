--ghc -package GLUT -lglut Program.hs -o Program
import Graphics.UI.GLUT
import Graphics.Rendering.OpenGL
import MandelbrotSet

main = do
    getArgsAndInitialize
    createAWindow "Mandelbrot Set"
    mainLoop

createAWindow windowName = do
    createWindow windowName
    displayCallback $= display

display = do
    clear [ColorBuffer]
    renderPrimitive Points(makeVertex points)
    flush

makeVertex = mapM_ (\(color, x, y, size) -> printPoint color x y size)

printPoint color x y size = do
    currentColor $= if color <= fromInteger(iterations) then Color4 (color / fromInteger(iterations  + 1)) 0.4 1 1 else Color4 0 0 0 1
    vertex (Vertex3 x y (0::GLfloat))
--    vertex (Vertex3 (x + size) y (0::GLfloat))
--    vertex (Vertex3 (x + size) (y + size) (0::GLfloat))
--    vertex (Vertex3 x (y + size) (0::GLfloat))

createPoints :: [[Integer]] -> [(GLfloat, GLfloat, GLfloat, GLfloat)]
createPoints arr = calculateY 0
                       where size = 2 / (fromIntegral $ max (length arr) (length $ arr !! 0))
                             calculateY :: Int -> [(GLfloat, GLfloat, GLfloat, GLfloat)]
                             calculateY y | y >= length arr = []
                                          | otherwise = calculateX 0 ++ calculateY (y + 1)
                                 where calculateX :: Int -> [(GLfloat, GLfloat, GLfloat, GLfloat)]
                                       calculateX x | x >= length (arr !! 0) = []
                                                    | otherwise = (fromInteger $ arr !! y !! x, fromIntegral(x) * size - 1, fromIntegral(y) * size - 1, size) : calculateX (x + 1)

iterations = 100
a = calculate standart iterations 300 200
--iterations = 550
--a = calculate first iterations 300 300
--iterations = 500
--a = calculate second iterations 300 300
--iterations = 3000
--a = calculate third iterations 300 300
--iterations = 100
--a = calculate fourth iterations 300 300
points = createPoints a

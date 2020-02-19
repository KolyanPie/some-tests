module MandelbrotSet where
import Data.Complex
import Debug.Trace

moduleSquare :: RealFloat a => Complex a -> a
moduleSquare complex = realPart complex ^ 2 + imagPart complex ^ 2

jumpCount :: RealFloat a => Complex a -> Integer -> Integer
jumpCount complex iterations = jump complex iterations 0
    where jump currentComplex iterations count | moduleSquare currentComplex > 4.0 = count
                                               | count > iterations = count
                                               | otherwise = jump (currentComplex * currentComplex + complex) iterations (count + 1)

data Frame = Frame Double Double Double Double
    deriving(Eq, Show)

calculate :: Frame -> Integer -> Integer -> Integer -> [[Integer]]
calculate (Frame x1 x2 y1 y2) iterations width height = calculateY 0
    where calculateY y | y >= height = []
                       | otherwise = calculateX 0 : calculateY (y + 1)
            where calculateX x | x >= width = trace ((show (y + 1)) ++ "/" ++ (show height)) []
                               | otherwise = jumpCount complex iterations : calculateX (x + 1)
                    where stepX = (x2 - x1) / fromIntegral width
                          stepY = (y2 - y1) / fromIntegral height
                          complex = (x1 + fromIntegral(x) * stepX) :+ (y1 + fromIntegral(y) * stepY)


standart = Frame (-2) 1 (-1) 1

--x = negate 1.7433419053321
--y = 0.0000907687489
--size = 0.00000000374

first = Frame (-1.7433419072021) (-1.7433419034621) (9.076687890000001e-5) (9.07706189e-5)

--x = (−1.88488933694469)
--y = 0.00000000081387
--size = 0.00000000000024

second = Frame (-1.8848893369448099) (-1.88488933694457) (8.1375e-10) (8.1399e-10)

--x = (−0.777807810193171)
--y = 0.131645108003206
--size = 0.0000000000000032

third = Frame (-0.7778078101931726) (-0.7778078101931695) (0.1316451080032044) (0.1316451080032076)

--x = (−0.56267837374)
--y = 0.65679461735
--size = 0.000000064

fourth = Frame (-0.5626784057399999) (-0.56267834174) (0.65679458535) (0.65679464935)









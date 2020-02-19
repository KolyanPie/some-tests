module Stream where
import qualified    Prelude as P    (head
                                    , tail
                                    , take
                                    , (!!)
                                    , map
                                    , filter
                                    , zip
                                    , zipWith
                                    , iterate
                                    )
import              Prelude hiding  (head
                                    , tail
                                    , take
                                    , (!!)
                                    , map
                                    , filter
                                    , zip
                                    , zipWith
                                    , iterate
                                    )
data Stream a = a :& Stream a

-- Возможность вывода потока в консоль
instance Show a => Show (Stream a) where
    show xs = showInfinity (show (take 5 xs))
        where showInfinity x = init x  ++ "..."

-- Первый элемент потока
head :: Stream a -> a 
head (x:&xs) = x

-- Хвост потока, всё кроме первого элемента
tail :: Stream a -> Stream a
tail (x:&xs) = xs

-- n-тый элемент потока
(!!) :: Stream a -> Int -> a
(!!) (x:&xs) n
    | n == 1    = x
    | n > 1     = (!!) xs (n - 1)
    | otherwise = undefined
-- Берёт из потока несколько первых элементов:
take :: Int -> Stream a -> [a]
take n (x:&xs) 
    | n == 0    = []
    | n > 0     = x : take (n - 1) xs
    | otherwise = undefined

-- Преобразование потока
map :: (a -> b) -> Stream a -> Stream b 
map f (y:&ys) = f y :& map f ys
-- Фильтрация потока
filter :: (a -> Bool) -> Stream a -> Stream a 
filter p (y:&ys) 
    | p y       = y :& (filter p ys)
    | otherwise = filter p ys

-- zip-ы для потоков:
zip :: Stream a -> Stream b -> Stream (a, b)
zip x y = zipWith (,) x y

zipWith :: (a -> b -> c) -> Stream a -> Stream b -> Stream c
zipWith f (x:&xs) (y:&ys) = f x y :& zipWith f xs ys


-- Генерация потоков
iterate :: (a -> a) -> a -> Stream a 
iterate f y = y  :& iterate f (f y)
	
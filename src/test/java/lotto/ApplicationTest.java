package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.constant.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("Application이 정상 작동하는지 확인한다.")
    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @DisplayName("잘못된 형태의 price를 입력받은 경우 예외를 발생시킨다.")
    @Test
    void PriceWrongTypeTest() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
            assertThat(output()).contains(ExceptionMessage.INVALID_PRICE_TYPE.getString());
        });
    }

    @DisplayName("잘못된 범위의 price를 입력받은 경우 예외를 발생시킨다.")
    @Test
    void PriceWrongRangeTest() {
        assertAll(
                () -> {
                    runException("-1000");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_PRICE_RANGE.getString());
                },
                () -> {
                    runException("-500");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_PRICE_RANGE.getString());
                },
                () -> {
                    runException("0");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_PRICE_RANGE.getString());
                });
    }

    @DisplayName("개당 가격으로 나누어떨어지지 않는 price를 입력받은 경우 예외를 발생시킨다.")
    @Test
    void PriceWrongValueTest() {
        assertAll(
                () -> {
                    runException("1500");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_PRICE_VALUE.getString());
                },
                () -> {
                    runException("3001");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_PRICE_VALUE.getString());
                });
    }

    @DisplayName("로또 정답의 형식이 잘못된 경우 예외를 발생시킨다.")
    @Test
    void LottoWrongTypeTest() {
        assertAll(
                () -> {
                    runException("2000", "1/2/3/4/5/6");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_LOTTO_TYPE.getString());
                },
                () -> {
                    runException("3000", "1, 2, 3, 4, 5, 6");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_LOTTO_TYPE.getString());
                },
                () -> {
                    runException("3000", "a,b,c,d,e,f");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_LOTTO_TYPE.getString());
                });
    }

    @DisplayName("보너스 숫자의 형식이 잘못된 경우 예외를 발생시킨다.")
    @Test
    void BonusWrongTypeTest() {
        assertAll(
                () -> {
                    runException("2000", "1,2,3,4,5,6", "1 ");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_BONUS_TYPE.getString());
                },
                () -> {
                    runException("2000", "1,2,3,4,5,6", "a");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_BONUS_TYPE.getString());
                },
                () -> {
                    runException("2000", "1,2,3,4,5,6", ".");
                    assertThat(output()).contains(ERROR_MESSAGE);
                    assertThat(output()).contains(ExceptionMessage.INVALID_BONUS_TYPE.getString());
                });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

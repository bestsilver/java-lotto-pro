package lotto_auto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FiguresTest {
    private Figures figures;
    @BeforeEach
    void setUp() {
        LottoNumbers lottoNumbers = new LottoNumbers(IntStream
                .rangeClosed(1, 6)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        Lottos lottos = new Lottos(Arrays.asList(new Lotto(lottoNumbers)));
        WinningLotto winning = new WinningLotto(lottoNumbers, new LottoNumber(7));
        figures = new Figures(lottos, winning);
    }
    @Test
    void 당첨_통계_결과_테스트() {
        assertAll(
            () -> assertThat(figures.getCountBy(LottoRank.FIRST)).isEqualTo(1),
            ()-> assertThat(figures.getCountBy(LottoRank.SECOND)).isEqualTo(0),
            ()-> assertThat(figures.getCountBy(LottoRank.THIRD)).isEqualTo(0),
            ()-> assertThat(figures.getCountBy(LottoRank.FOURTH)).isEqualTo(0)
        );
    }

    @Test
    void 수익금_결과_테스트() {
        assertThat(figures.getTotalWinning()).isEqualTo(LottoRank.FIRST.winnings());
    }
}

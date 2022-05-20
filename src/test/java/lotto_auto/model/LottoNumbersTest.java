package lotto_auto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class LottoNumbersTest {
    @Test
    public void 로또번호_개수_체크() {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6), new LottoNumber(7)));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoNumbers(lottoNumberSet))
                .withMessage(LottoNumbers.NOT_MATCHED_NUMBER_SIZE);
    }

    @Test
    public void 로또번호_중복_체크() {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(Arrays.asList(new LottoNumber(1),
                new LottoNumber(1),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6)));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoNumbers(lottoNumberSet))
                .withMessage(LottoNumbers.EXIST_DUPLICATE_VALUE);
    }


    @Test
    public void 외부에서_로또_번호_추가_시_에러_발생() {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6)));
        LottoNumbers numbers = new LottoNumbers(lottoNumberSet);
        Assertions.assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> numbers.getLottoNumberSet().add(new LottoNumber(7)));
    }
}

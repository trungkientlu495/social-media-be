package social.media.network.payload.paging;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class Page<T> {
    private int page=0;
    private int pageSize=2;
    private List<T> contents;
}

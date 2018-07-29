package com.chariot.shadow.news.vtv;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class VtvTechnologyArticleEntryTest {

    @Tested
    private VtvTechnologyArticleEntry news;
    @Injectable
    private SyndFeed feed;
    @Injectable
    private SyndEntry entry;

    @Test
    public void getContent(@Mocked SyndEntry mockedEntry) {
        new Expectations(news) {{
            news.getEntry();
            result = mockedEntry;
            mockedEntry.getDescription().getValue();
            result = "<a href=\"https://vtv.vn/cong-nghe/day-manh-lien-ket-trong-phat-trien-khcn-vung-dbscl-20180727181030899.htm\"><img src=\"https://vtv1.mediacdn.vn/zoom/80_50/2018/7/27/anh-3-tang-hoa-nghi-huu-va-tang-hoa-chuyen-giao-giao-ban-2020-cho-soc-trang-15326894565701559478925.jpg\" /></a>VTV.vn - Ngày 27/7, Bộ KH&CN phối hợp với UBND tỉnh Tiền Giang tổ chức Hội nghị Giao ban KH&CN vùng ĐBSCL lần thứ 25 tại Mỹ Tho với sự tham gia của 13 tỉnh, thành phố.";
        }};

        assertThat(news.getContent(), is("Ngày 27/7, Bộ KH&CN phối hợp với UBND tỉnh Tiền Giang tổ chức Hội nghị Giao ban KH&CN vùng ĐBSCL lần thứ 25 tại Mỹ Tho với sự tham gia của 13 tỉnh, thành phố."));
    }

    @Test
    public void getImage(@Mocked SyndEntry mockedEntry) throws Exception {
        String source = "<a href=\"https://vtv.vn/cong-nghe/day-manh-lien-ket-trong-phat-trien-khcn-vung-dbscl-20180727181030899.htm\"><img src=\"https://vtv1.mediacdn.vn/zoom/80_50/2018/7/27/anh-3-tang-hoa-nghi-huu-va-tang-hoa-chuyen-giao-giao-ban-2020-cho-soc-trang-15326894565701559478925.jpg\" /></a>VTV.vn - Ngày 27/7, Bộ KH&CN phối hợp với UBND tỉnh Tiền Giang tổ chức Hội nghị Giao ban KH&CN vùng ĐBSCL lần thứ 25 tại Mỹ Tho với sự tham gia của 13 tỉnh, thành phố.";
        new Expectations(news) {{
            news.getEntry();
            result = mockedEntry;
            mockedEntry.getDescription().getValue();
            result = source;
        }};

        assertThat(news.getImage(), is("https://vtv1.mediacdn.vn/zoom/80_50/2018/7/27/anh-3-tang-hoa-nghi-huu-va-tang-hoa-chuyen-giao-giao-ban-2020-cho-soc-trang-15326894565701559478925.jpg"));
    }
}